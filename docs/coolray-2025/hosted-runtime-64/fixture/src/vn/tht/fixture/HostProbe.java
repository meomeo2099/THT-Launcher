package vn.tht.fixture;
import android.app.*;
import android.content.*;
import android.graphics.*;
import android.hardware.display.DisplayManager;
import android.os.*;
import android.provider.Settings;
import android.view.*;
import android.widget.*;
import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.Callable;
import org.json.*;

public class HostProbe extends Instrumentation {
 private String scenario; private Activity main; private Object split; private Context c;
 private JSONArray observations=new JSONArray(); private Throwable asyncError;
 public void onCreate(Bundle args){super.onCreate(args);scenario=args.getString("scenario","host");start();}
 private Object ui(Callable<Object> action)throws Exception{Object[] out=new Object[1];Throwable[] err=new Throwable[1];runOnMainSync(()->{try{out[0]=action.call();}catch(Throwable t){err[0]=t;}});if(err[0]!=null)throw new Exception(err[0]);return out[0];}
 private Object invoke(Object obj,String name,Class<?>[] types,Object...args)throws Exception{Method m=obj.getClass().getDeclaredMethod(name,types);m.setAccessible(true);return m.invoke(obj,args);}
 private Object am(String name,Class<?>[] types,Object...args)throws Exception{Object service=ActivityManager.class.getMethod("getService").invoke(null);return Class.forName("android.app.IActivityManager").getMethod(name,types).invoke(service,args);}
 private Object pair()throws Exception{return c.getClassLoader().loadClass("vn.tht.launcher.split.Split").getConstructor(String.class,String.class,String.class,int.class).newInstance("host-probe","vn.tht.fixture","vn.tht.fixtureb",50);}
 private List<?> stacks()throws Exception{return (List<?>)am("getAllStackInfos",new Class<?>[0]);}
 private Object target(String pkg)throws Exception{for(Object info:stacks()){Class<?> cl=info.getClass();if(cl.getField("displayId").getInt(info)<1)continue;String names=Arrays.toString((Object[])cl.getField("taskNames").get(info));if(names.contains(pkg+"/"))return info;}return null;}
 private int display(Object info)throws Exception{return info.getClass().getField("displayId").getInt(info);}
 private void noNative()throws Exception{for(int mode:new int[]{3,4})if(am("getStackInfo",new Class<?>[]{int.class,int.class},mode,1)!=null)throw new AssertionError("Native split present");}
 private void await(Callable<Boolean> condition,String why)throws Exception{long end=SystemClock.uptimeMillis()+30000;while(SystemClock.uptimeMillis()<end){if(asyncError!=null)throw new Exception(asyncError);if(condition.call())return;Thread.sleep(150);}throw new AssertionError(why+" "+ui(()->invoke(split,"diagnosticReport",new Class<?>[0])));}
 private void record(String phase)throws Exception{JSONObject out=new JSONObject().put("phase",phase);JSONArray rows=new JSONArray();for(String pkg:new String[]{"vn.tht.fixture","vn.tht.fixtureb"}){Object info=target(pkg);if(info==null)continue;int id=display(info);Point size=new Point();c.getSystemService(DisplayManager.class).getDisplay(id).getRealSize(size);Rect bounds=(Rect)info.getClass().getField("bounds").get(info);rows.put(new JSONObject().put("package",pkg).put("display",id).put("bounds",bounds.toString()).put("width",size.x).put("height",size.y));}out.put("panes",rows);observations.put(out);try(java.io.FileOutputStream f=new java.io.FileOutputStream(new java.io.File(c.getFilesDir(),"host-probe.json"))){f.write(out.toString().getBytes("UTF-8"));}}
 private boolean correctPair(float fraction)throws Exception{Object a=target("vn.tht.fixture"),b=target("vn.tht.fixtureb");if(a==null||b==null||display(a)==display(b))return false;Point x=new Point(),y=new Point();DisplayManager dm=c.getSystemService(DisplayManager.class);if(dm.getDisplay(display(a))==null||dm.getDisplay(display(b))==null)return false;dm.getDisplay(display(a)).getRealSize(x);dm.getDisplay(display(b)).getRealSize(y);Rect ab=(Rect)a.getClass().getField("bounds").get(a),bb=(Rect)b.getClass().getField("bounds").get(b);return x.y==y.y&&Math.abs(x.x/(float)(x.x+y.x)-fraction)<.025&&ab.equals(new Rect(0,0,x.x,x.y))&&bb.equals(new Rect(0,0,y.x,y.y));}
 private View find(View v,String text){if(v instanceof TextView&&text.contentEquals(((TextView)v).getText()))return v;if(v instanceof ViewGroup){ViewGroup g=(ViewGroup)v;for(int i=0;i<g.getChildCount();i++){View r=find(g.getChildAt(i),text);if(r!=null)return r;}}return null;}
 public void onStart(){Bundle result=new Bundle();View map=null;FrameLayout mapBox=null;SharedPreferences prefs=null;String oldMode=null;
  try{c=getTargetContext();prefs=c.getSharedPreferences("tht",0);prefs.edit().putBoolean("embed_map",false).putString("split_backend","auto").apply();main=startActivitySync(new Intent().setClassName(c.getPackageName(),"vn.tht.launcher.MainActivity").addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));split=ui(()->invoke(main,"getSplitScreen",new Class<?>[0]));Thread.sleep(700);final Object request=pair();
   if(scenario.equals("host")||scenario.equals("hostcancel")||scenario.equals("hostexternal")){
    ui(()->{invoke(split,"open",new Class<?>[]{request.getClass()},request);if(scenario.equals("hostcancel"))invoke(split,"close",new Class<?>[0]);return null;});
    if(!scenario.equals("hostcancel")){
     await(()->correctPair(.5f),"Two virtual displays not ready");noNative();record("host50");Thread.sleep(4000);
     if(scenario.equals("host")){int oldA=display(target("vn.tht.fixture")),oldB=display(target("vn.tht.fixtureb"));ui(()->invoke(split,"place",new Class<?>[]{int.class},30));await(()->correctPair(.3f)&&display(target("vn.tht.fixture"))==oldA&&display(target("vn.tht.fixtureb"))==oldB,"Resize did not keep matching owned displays");noNative();record("host30_resized");Thread.sleep(4000);}
     if(scenario.equals("hostexternal")){ui(()->{Class<?> cl=c.getClassLoader().loadClass("vn.tht.launcher.system.ExternalAppLauncher");Object ok=cl.getMethod("launch",Context.class,Intent.class).invoke(cl.getField("INSTANCE").get(null),c,c.getPackageManager().getLaunchIntentForPackage("vn.tht.fixture"));if(!Boolean.TRUE.equals(ok))throw new AssertionError("External rejected");return null;});}
     else ui(()->invoke(split,"close",new Class<?>[0]));
    }
    Thread.sleep(1500);noNative();if((Boolean)ui(()->invoke(split,"getHasWork",new Class<?>[0])))throw new AssertionError("Owner remained");if(target("vn.tht.fixture")!=null||target("vn.tht.fixtureb")!=null)throw new AssertionError("Owned virtual task survived close");record("host_closed");
   }else if(scenario.equals("widget")){
    ClassLoader loader=c.getClassLoader();Class<?> embeddedClass=loader.loadClass("vn.tht.launcher.home.EmbeddedMap");Constructor<?> factory=Arrays.stream(embeddedClass.getConstructors()).filter(v->v.getParameterTypes().length==4).findFirst().get();Class<?> f0=factory.getParameterTypes()[2],f1=factory.getParameterTypes()[3];Object unit=null;
    Object started=Proxy.newProxyInstance(loader,new Class<?>[]{f0},(o,m,a)->unit);Object failed=Proxy.newProxyInstance(loader,new Class<?>[]{f1},(o,m,a)->{if(m.getName().equals("invoke"))asyncError=new AssertionError(String.valueOf(a[0]));return unit;});
    Object embedded=factory.newInstance(main,false,started,failed);map=(View)embedded;final View mapView=map;
    mapBox=(FrameLayout)ui(()->{FrameLayout box=new FrameLayout(main);box.addView(mapView,new FrameLayout.LayoutParams(-1,-1));((ViewGroup)main.findViewById(android.R.id.content)).addView(box,new FrameLayout.LayoutParams(700,400));return box;});final FrameLayout box=mapBox;
    ui(()->{invoke(embedded,"create",new Class<?>[0]);invoke(embedded,"setActive",new Class<?>[]{boolean.class},true);invoke(embedded,"start",new Class<?>[]{Intent.class},c.getPackageManager().getLaunchIntentForPackage("vn.tht.fixture"));return null;});
    await(()->{Object a=target("vn.tht.fixture");if(a==null)return false;Point p=new Point();c.getSystemService(DisplayManager.class).getDisplay(display(a)).getRealSize(p);return p.x==700&&p.y==400;},"Initial map size mismatch");int old=display(target("vn.tht.fixture"));record("widget700x400");
    ui(()->{ViewGroup.LayoutParams lp=box.getLayoutParams();lp.width=1200;lp.height=500;box.setLayoutParams(lp);return null;});
    await(()->{Object a=target("vn.tht.fixture");if(a==null||display(a)!=old)return false;Point p=new Point();c.getSystemService(DisplayManager.class).getDisplay(display(a)).getRealSize(p);return p.x==1200&&p.y==500;},"Resized map inherited stale size");record("widget1200x500_same_display");Thread.sleep(4000);ui(()->invoke(embedded,"release",new Class<?>[0]));
   }else if(scenario.equals("insets")){
    oldMode=prefs.getString("nav_mode","buttons");prefs.edit().putString("nav_mode","bar").putBoolean("nav_bar_home",true).apply();Class<?> service=c.getClassLoader().loadClass("vn.tht.launcher.system.NavAssistService");Object companion=service.getField("Companion").get(null);ui(()->companion.getClass().getMethod("tryEnable",Context.class).invoke(companion,c));Thread.sleep(2000);
    Activity settings=startActivitySync(new Intent().setClassName(c.getPackageName(),"vn.tht.launcher.settings.SettingsActivity").addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));Thread.sleep(700);
    Field instance=service.getDeclaredField("instance");instance.setAccessible(true);Object svc=instance.get(null);if(svc==null)throw new AssertionError("Accessibility bar not connected");Field bf=service.getDeclaredField("bar");bf.setAccessible(true);View bar=(View)bf.get(svc);if(bar==null)throw new AssertionError("No navigation rail");View title=find(settings.findViewById(android.R.id.content),"THT Launcher");int[] a=new int[2],b=new int[2];ui(()->{bar.getLocationOnScreen(a);title.getLocationOnScreen(b);return null;});if(b[0]<a[0]+bar.getWidth())throw new AssertionError("Settings hidden by rail: "+b[0]+" < "+(a[0]+bar.getWidth()));observations.put(new JSONObject().put("phase","settings_clear_of_rail").put("railRight",a[0]+bar.getWidth()).put("menuLeft",b[0]));record("settings_insets");Thread.sleep(4000);ui(()->{settings.finish();return null;});
   }
   result.putString("result","PASS");
  }catch(Throwable t){try{record("failure");Class<?> hc=c.getClassLoader().loadClass("vn.tht.launcher.split.HostedSplitActivity");Field af=hc.getDeclaredField("active");af.setAccessible(true);Object ref=af.get(null);Object host=ref==null?null:((java.lang.ref.WeakReference<?>)ref).get();if(host!=null){Field mf=hc.getDeclaredField("maps");mf.setAccessible(true);JSONArray states=new JSONArray();for(Object em:(java.util.List<?>)mf.get(host)){JSONObject st=new JSONObject();for(String key:new String[]{"active","released","startedWidth","startedHeight","ready"}){Field f=em.getClass().getDeclaredField(key);f.setAccessible(true);st.put(key,f.get(em));}states.put(st);}observations.put(new JSONObject().put("map_states",states));}}catch(Throwable ignored){}result.putString("result","FAIL");result.putString("error",t.toString());}
  finally{try{if(split!=null)ui(()->invoke(split,"close",new Class<?>[0]));}catch(Throwable ignored){}if(prefs!=null&&oldMode!=null)prefs.edit().putString("nav_mode",oldMode).apply();if(mapBox!=null){final View box=mapBox;try{ui(()->{((ViewGroup)box.getParent()).removeView(box);return null;});}catch(Throwable ignored){}}result.putString("scenario",scenario);result.putString("observations",observations.toString());finish(0,result);}
 }
}
