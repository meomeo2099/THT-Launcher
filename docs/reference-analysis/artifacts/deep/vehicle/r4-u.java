    public final ClassLoader u() {
        Object ffVar;
        ClassLoader classLoader = this.h;
        if (classLoader == null) {
            classLoader = this.a.getClassLoader();
            try {
                ffVar = classLoader.loadClass("com.ecarx.xui.adaptapi.car.impl.CarImpl");
            } catch (Throwable th) {
                ffVar = new ff(th);
            }
            if (ffVar instanceof ff) {
                classLoader = new PathClassLoader("/system/framework/AdapterAPI.jar:/system/framework/AdapterAPIImpl.jar:/system/framework/ecarx.jar:/system/framework/ecarxSignalAPI.jar", classLoader);
            }
            this.h = classLoader;
            classLoader.getClass();
        }
        return classLoader;
    }
