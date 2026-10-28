    public final void a() throws IllegalAccessException, InstantiationException, ClassNotFoundException, InvocationTargetException {
        Object ffVar;
        ClassLoader classLoaderU = u();
        Object objNewInstance = this.i;
        if (objNewInstance == null) {
            objNewInstance = classLoaderU.loadClass("com.ecarx.xui.adaptapi.car.impl.CarImpl").getConstructor(Context.class).newInstance(this.a);
            this.i = objNewInstance;
        }
        try {
            ffVar = classLoaderU.loadClass("com.ecarx.xui.adaptapi.binder.IConnectable");
        } catch (Throwable th) {
            ffVar = new ff(th);
        }
        if (ffVar instanceof ff) {
            ffVar = null;
        }
        Class cls = (Class) ffVar;
        if (cls == null || !cls.isInstance(objNewInstance)) {
            objNewInstance.getClass();
            g(objNewInstance);
            return;
        }
        if (!this.k) {
            Class<?> clsLoadClass = classLoaderU.loadClass("com.ecarx.xui.adaptapi.binder.IConnectable$IConnectWatcher");
            cls.getMethod("registerConnectWatcher", clsLoadClass).invoke(objNewInstance, Proxy.newProxyInstance(clsLoadClass.getClassLoader(), new Class[]{clsLoadClass}, new p4(this, 1)));
            this.k = true;
        }
        cls.getMethod("connect", null).invoke(objNewInstance, null);
    }
