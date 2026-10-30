package vn.tht.fixture;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.SystemClock;
import org.json.JSONArray;
import org.json.JSONObject;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;

/** Test-only instrumentation, signed like the target. Not packaged in THT. */
public class Probe extends Instrumentation {
    private Activity activity;
    private Object split;
    private Class<?> plan;
    private JSONArray observations = new JSONArray();
    private String scenario;

    public void onCreate(Bundle args) { super.onCreate(args); scenario=args.getString("scenario", "normal"); start(); }
    private Object ui(Callable<Object> action) throws Exception {
        Object[] result=new Object[1]; Throwable[] error=new Throwable[1];
        runOnMainSync(() -> { try { result[0]=action.call(); } catch(Throwable t) { error[0]=t; } });
        if(error[0]!=null) throw new Exception(error[0]);
        return result[0];
    }
    private Object call(String name, Class<?>[] types, Object... values) throws Exception {
        Method m=split.getClass().getDeclaredMethod(name,types);m.setAccessible(true);return m.invoke(split,values);
    }
    private Object request(String left,String right,int ratio) throws Exception {
        return plan.getConstructor(String.class,String.class,String.class,int.class).newInstance("regression",left,right,ratio);
    }
    private Object stack(int mode) throws Exception {
        Object service=android.app.ActivityManager.class.getMethod("getService").invoke(null);
        return Class.forName("android.app.IActivityManager").getMethod("getStackInfo",int.class,int.class).invoke(service,mode,1);
    }
    private JSONObject snapshot(String phase) throws Exception {
        JSONObject value=new JSONObject().put("phase",phase);
        for(int mode:new int[]{3,4}) {
            Object s=stack(mode);
            if(s==null) { value.put("mode"+mode,JSONObject.NULL);continue; }
            Class<?> c=s.getClass();
            value.put("mode"+mode,new JSONObject().put("bounds",c.getField("bounds").get(s).toString())
                .put("display",c.getField("displayId").getInt(s)).put("visible",c.getField("visible").getBoolean(s))
                .put("names",new JSONArray((Object[])c.getField("taskNames").get(s))));
        }
        value.put("state",ui(() -> call("diagnosticReport",new Class<?>[0])));
        observations.put(value);
        try(java.io.FileOutputStream out=new java.io.FileOutputStream(new java.io.File(getTargetContext().getFilesDir(),"split-probe-state.json"))) { out.write(value.toString().getBytes("UTF-8")); }
        return value;
    }
    private void awaitOpen() throws Exception {
        long until=SystemClock.uptimeMillis()+20000;
        while(SystemClock.uptimeMillis()<until) {
            if(ui(() -> call("getOpenId",new Class<?>[0]))!=null)return;
            Thread.sleep(100);
        }
        throw new AssertionError("Split not committed: "+ui(() -> call("diagnosticReport",new Class<?>[0])));
    }
    private void assertNoSplit() throws Exception {
        if(stack(3)!=null || stack(4)!=null) throw new AssertionError("Split remained after close");
        if((Boolean)ui(() -> call("getHasWork",new Class<?>[0]))) throw new AssertionError("Owner remained after close");
        Object service=android.app.ActivityManager.class.getMethod("getService").invoke(null);
        java.util.List<?> all=(java.util.List<?>)Class.forName("android.app.IActivityManager").getMethod("getAllStackInfos").invoke(service);
        for(Object info:all) {
            String names=java.util.Arrays.toString((Object[])info.getClass().getField("taskNames").get(info));
            if(names.contains("vn.tht.fixture")) {
                Rect b=(Rect)info.getClass().getField("bounds").get(info);
                if(b.left!=0||b.right!=1920||b.top!=0||b.bottom!=720) throw new AssertionError("Stale fullscreen bounds after close: "+b);
            }
        }
    }
    private void assertPair(String left, String right, int ratio) throws Exception {
        Object a=stack(3),b=stack(4);
        if(a==null||b==null)throw new AssertionError("Missing pane");
        String la=String.valueOf(a.getClass().getField("topActivity").get(a));
        String rb=String.valueOf(b.getClass().getField("topActivity").get(b));
        if(!la.contains(left+"/")||!rb.contains(right+"/"))throw new AssertionError("Wrong apps: "+la+" "+rb);
        Rect x=(Rect)a.getClass().getField("bounds").get(a),y=(Rect)b.getClass().getField("bounds").get(b);
        if(Math.abs(x.right-(1920*ratio/100))>16||Math.abs(y.left-x.right)>16||x.bottom>588||y.bottom>588)
            throw new AssertionError("Wrong geometry: "+x+" "+y);
    }
    public void onStart() {
        Bundle result=new Bundle();
        try {
            Context c=getTargetContext();
            activity=startActivitySync(new Intent().setClassName("vn.tht.launcher","vn.tht.launcher.MainActivity").addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            split=ui(() -> { Method m=activity.getClass().getDeclaredMethod("getSplitScreen");m.setAccessible(true);return m.invoke(activity); });
            plan=c.getClassLoader().loadClass("vn.tht.launcher.split.Split");
            final Object pair=request("vn.tht.fixture","vn.tht.fixtureb",50);
            if(scenario.equals("normal")||scenario.equals("visual")||scenario.equals("settings")) {
                // Schedule normal fullscreen repairs, then immediately request split: old repairs must not undo it.
                ui(() -> {
                    Class<?> launcher=c.getClassLoader().loadClass("vn.tht.launcher.system.ExternalAppLauncher");
                    Object instance=launcher.getField("INSTANCE").get(null);
                    launcher.getMethod("launch",Context.class,Intent.class).invoke(instance,c,c.getPackageManager().getLaunchIntentForPackage("vn.tht.fixture"));
                    return call("open",new Class<?>[]{plan},pair);
                });
                awaitOpen(); Thread.sleep(3500);assertPair("vn.tht.fixture","vn.tht.fixtureb",50);snapshot("open50_after_old_fullscreen_timers");
                if(scenario.equals("visual")) Thread.sleep(6000);
                if(!(Boolean)ui(() -> call("place",new Class<?>[]{int.class},30)))throw new AssertionError("Resize rejected");
                Thread.sleep(700);assertPair("vn.tht.fixture","vn.tht.fixtureb",30);snapshot("ratio30");
                if(scenario.equals("visual")) Thread.sleep(6000);
                ui(() -> {
                    Object service=android.app.ActivityManager.class.getMethod("getService").invoke(null);
                    Class<?> am=Class.forName("android.app.IActivityManager");
                    am.getMethod("resizeDockedStack",Rect.class,Rect.class,Rect.class,Rect.class,Rect.class).invoke(service,new Rect(0,0,1152,720),null,null,null,null);
                    Object secondary=stack(4);int id=secondary.getClass().getField("stackId").getInt(secondary);
                    am.getMethod("resizeStack",int.class,Rect.class,boolean.class,boolean.class,boolean.class,int.class).invoke(service,id,new Rect(1152,0,1920,720),true,false,false,-1);
                    return null;
                });
                Thread.sleep(3000);assertPair("vn.tht.fixture","vn.tht.fixtureb",60);snapshot("native_bounds_refitted60");
                ui(() -> call("swap",new Class<?>[0]));awaitOpen();Thread.sleep(700);
                assertPair("vn.tht.fixtureb","vn.tht.fixture",40);snapshot("swapped40");
                if(scenario.equals("visual")) Thread.sleep(6000);
                if(scenario.equals("settings"))ui(() -> {activity.getClass().getMethod("openSettings").invoke(activity);return null;});
                else if(!(Boolean)ui(() -> call("close",new Class<?>[0])))throw new AssertionError("Close rejected");
                Thread.sleep(700);assertNoSplit();snapshot("closed");
            } else {
                final String[] before=new String[1];
                ui(() -> {
                    call("open",new Class<?>[]{plan},pair);
                    before[0]=String.valueOf(call("diagnosticReport",new Class<?>[0]));
                    if(scenario.equals("release"))return call("release",new Class<?>[0]);
                    if(scenario.equals("fullscreen")) {
                        Class<?> launcher=c.getClassLoader().loadClass("vn.tht.launcher.system.ExternalAppLauncher");
                        return launcher.getMethod("launch",Context.class,Intent.class).invoke(launcher.getField("INSTANCE").get(null),c,c.getPackageManager().getLaunchIntentForPackage("vn.tht.fixture"));
                    }
                    return call("close",new Class<?>[0]);
                });
                observations.put(new JSONObject().put("phase","before_cancel").put("state",before[0]));
                Thread.sleep(3500);assertNoSplit();snapshot("cancelled_no_resurrection");
            }
            result.putString("result","PASS");
        } catch(Throwable t) { result.putString("result","FAIL");result.putString("error",t.toString());
            try { observations.put(new JSONObject().put("diagnostic",ui(() -> call("diagnosticReport",new Class<?>[0])))); } catch(Throwable ignored) {}
        } finally {
            try { if(split!=null)ui(() -> call("close",new Class<?>[0])); } catch(Throwable ignored) {}
            result.putString("scenario",scenario);result.putString("observations",observations.toString());finish(0,result);
        }
    }
}
