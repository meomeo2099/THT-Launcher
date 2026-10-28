    /* JADX WARN: Code duplicated, block: B:101:0x01f6  */
    /* JADX WARN: Code duplicated, block: B:126:0x01ea A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:66:0x0172  */
    /* JADX WARN: Code duplicated, block: B:67:0x0175  */
    /* JADX WARN: Code duplicated, block: B:69:0x0179 A[Catch: all -> 0x007f, TRY_LEAVE, TryCatch #5 {all -> 0x007f, blocks: (B:25:0x005b, B:27:0x006a, B:31:0x0082, B:32:0x00ec, B:34:0x00f2, B:44:0x0129, B:47:0x012f, B:57:0x015e, B:60:0x0164, B:102:0x01f7, B:69:0x0179, B:78:0x01a6, B:80:0x01ac, B:82:0x01b0, B:85:0x01b7, B:89:0x01bf, B:90:0x01db, B:93:0x01e0, B:95:0x01e8, B:96:0x01ea, B:77:0x01a0, B:56:0x0158, B:43:0x0123, B:49:0x0139, B:51:0x0151, B:70:0x017b, B:72:0x0199, B:36:0x0102, B:38:0x011c), top: B:122:0x005b, inners: #0, #3, #4 }] */
    /* JADX WARN: Code duplicated, block: B:72:0x0199 A[Catch: all -> 0x019c, TRY_LEAVE, TryCatch #3 {all -> 0x019c, blocks: (B:70:0x017b, B:72:0x0199), top: B:118:0x017b, outer: #5 }] */
    /* JADX WARN: Code duplicated, block: B:76:0x019e  */
    /* JADX WARN: Code duplicated, block: B:80:0x01ac A[Catch: all -> 0x007f, TryCatch #5 {all -> 0x007f, blocks: (B:25:0x005b, B:27:0x006a, B:31:0x0082, B:32:0x00ec, B:34:0x00f2, B:44:0x0129, B:47:0x012f, B:57:0x015e, B:60:0x0164, B:102:0x01f7, B:69:0x0179, B:78:0x01a6, B:80:0x01ac, B:82:0x01b0, B:85:0x01b7, B:89:0x01bf, B:90:0x01db, B:93:0x01e0, B:95:0x01e8, B:96:0x01ea, B:77:0x01a0, B:56:0x0158, B:43:0x0123, B:49:0x0139, B:51:0x0151, B:70:0x017b, B:72:0x0199, B:36:0x0102, B:38:0x011c), top: B:122:0x005b, inners: #0, #3, #4 }] */
    /* JADX WARN: Code duplicated, block: B:82:0x01b0 A[Catch: all -> 0x007f, TryCatch #5 {all -> 0x007f, blocks: (B:25:0x005b, B:27:0x006a, B:31:0x0082, B:32:0x00ec, B:34:0x00f2, B:44:0x0129, B:47:0x012f, B:57:0x015e, B:60:0x0164, B:102:0x01f7, B:69:0x0179, B:78:0x01a6, B:80:0x01ac, B:82:0x01b0, B:85:0x01b7, B:89:0x01bf, B:90:0x01db, B:93:0x01e0, B:95:0x01e8, B:96:0x01ea, B:77:0x01a0, B:56:0x0158, B:43:0x0123, B:49:0x0139, B:51:0x0151, B:70:0x017b, B:72:0x0199, B:36:0x0102, B:38:0x011c), top: B:122:0x005b, inners: #0, #3, #4 }] */
    /* JADX WARN: Code duplicated, block: B:83:0x01b4  */
    /* JADX WARN: Code duplicated, block: B:92:0x01df  */
    /* JADX WARN: Code duplicated, block: B:95:0x01e8 A[Catch: all -> 0x007f, TryCatch #5 {all -> 0x007f, blocks: (B:25:0x005b, B:27:0x006a, B:31:0x0082, B:32:0x00ec, B:34:0x00f2, B:44:0x0129, B:47:0x012f, B:57:0x015e, B:60:0x0164, B:102:0x01f7, B:69:0x0179, B:78:0x01a6, B:80:0x01ac, B:82:0x01b0, B:85:0x01b7, B:89:0x01bf, B:90:0x01db, B:93:0x01e0, B:95:0x01e8, B:96:0x01ea, B:77:0x01a0, B:56:0x0158, B:43:0x0123, B:49:0x0139, B:51:0x0151, B:70:0x017b, B:72:0x0199, B:36:0x0102, B:38:0x011c), top: B:122:0x005b, inners: #0, #3, #4 }] */
    public final void g(Object obj) {
        Object ffVar;
        Object ffVar2;
        Object ffVar3;
        Object ffVar4;
        boolean z;
        int i;
        int i2;
        int i3;
        Object ffVar5;
        Throwable thA;
        InvocationTargetException invocationTargetException;
        Throwable targetException;
        Object objInvoke;
        boolean z2;
        try {
            ffVar = obj.getClass().getMethod("getICarFunction", null).invoke(obj, null);
        } catch (Throwable th) {
            ffVar = new ff(th);
        }
        Throwable thA2 = gf.a(ffVar);
        if (thA2 != null) {
            Log.e("ReferencePanel.Ecarx", "getICarFunction", thA2);
        }
        Object obj2 = ffVar instanceof ff ? null : ffVar;
        synchronized (this.b) {
            this.j = obj2;
        }
        this.m = null;
        this.d.clear();
        this.e = null;
        this.n = obj2 != null ? new j(4) : new j(2);
        if (obj2 != null) {
            try {
                Class<?> clsLoadClass = u().loadClass("com.ecarx.xui.adaptapi.car.base.ICarFunction$IFunctionValueWatcher");
                Object objNewProxyInstance = this.y;
                int i4 = 0;
                if (objNewProxyInstance == null) {
                    objNewProxyInstance = Proxy.newProxyInstance(clsLoadClass.getClassLoader(), new Class[]{clsLoadClass}, new p4(this, i4));
                    this.y = objNewProxyInstance;
                }
                Object obj3 = objNewProxyInstance;
                List list = z2.a;
                Set setKeySet = z2.e().keySet();
                setKeySet.getClass();
                LinkedHashSet linkedHashSetH0 = zf.h0(setKeySet, j3.i0(268828928, 268763648, 268763392, 553846272, 553845760, 553979904, 704709376, 570491136, 537002240, 269484800));
                int size = linkedHashSetH0.size();
                int[] iArr = new int[size];
                Iterator it = linkedHashSetH0.iterator();
                int i5 = 0;
                while (it.hasNext()) {
                    int i6 = i5 + 1;
                    iArr[i5] = ((Number) it.next()).intValue();
                    i5 = i6;
                }
                try {
                    Object objInvoke2 = obj2.getClass().getMethod("registerFunctionValueWatcher", int[].class, clsLoadClass).invoke(obj2, iArr, obj3);
                    ffVar3 = objInvoke2 instanceof Boolean ? (Boolean) objInvoke2 : null;
                } catch (Throwable th2) {
                    ffVar3 = new ff(th2);
                }
                if (ffVar3 instanceof ff) {
                    ffVar3 = null;
                }
                if (k8.e((Boolean) ffVar3, Boolean.TRUE)) {
                    if (z) {
                        i2 = 0;
                    } else {
                        i2 = 0;
                        for (i = 0; i < size; i++) {
                            i3 = iArr[i];
                            objInvoke = obj2.getClass().getMethod("registerFunctionValueWatcher", this.f, clsLoadClass).invoke(obj2, Integer.valueOf(i3), obj3);
                            if (objInvoke instanceof Boolean) {
                                ffVar5 = (Boolean) objInvoke;
                            } else {
                                ffVar5 = null;
                            }
                            thA = gf.a(ffVar5);
                            if (thA != null) {
                                if (thA instanceof InvocationTargetException) {
                                    invocationTargetException = (InvocationTargetException) thA;
                                } else {
                                    invocationTargetException = null;
                                }
                                if (invocationTargetException != null) {
                                    thA = targetException;
                                }
                                Log.w("ReferencePanel.Ecarx", "watcher " + i3 + ": " + thA);
                            }
                            if (ffVar5 instanceof ff) {
                                ffVar5 = null;
                            }
                            if (k8.e(ffVar5, Boolean.TRUE)) {
                                i2++;
                            }
                        }
                    }
                    if (z) {
                        z2 = true;
                    } else {
                        z2 = true;
                    }
                    this.z = z2;
                    ffVar2 = Integer.valueOf(Log.i("ReferencePanel.Ecarx", "registerFunctionValueWatcher: all=" + z + ", by function=" + i2 + " of " + size));
                } else {
                    try {
                        Object objInvoke3 = obj2.getClass().getMethod("registerFunctionValueWatcher", clsLoadClass).invoke(obj2, obj3);
                        ffVar4 = objInvoke3 instanceof Boolean ? (Boolean) objInvoke3 : null;
                    } catch (Throwable th3) {
                        ffVar4 = new ff(th3);
                    }
                    if (ffVar4 instanceof ff) {
                        ffVar4 = null;
                    }
                    z = k8.e(ffVar4, Boolean.TRUE);
                    if (z) {
                        i2 = 0;
                    } else {
                        i2 = 0;
                        while (i < size) {
                            i3 = iArr[i];
                            try {
                                objInvoke = obj2.getClass().getMethod("registerFunctionValueWatcher", this.f, clsLoadClass).invoke(obj2, Integer.valueOf(i3), obj3);
                                if (objInvoke instanceof Boolean) {
                                    ffVar5 = (Boolean) objInvoke;
                                } else {
                                    ffVar5 = null;
                                }
                            } catch (Throwable th4) {
                                ffVar5 = new ff(th4);
                            }
                            thA = gf.a(ffVar5);
                            if (thA != null) {
                                if (thA instanceof InvocationTargetException) {
                                    invocationTargetException = (InvocationTargetException) thA;
                                } else {
                                    invocationTargetException = null;
                                }
                                if (invocationTargetException != null && (targetException = invocationTargetException.getTargetException()) != null) {
                                    thA = targetException;
                                }
                                Log.w("ReferencePanel.Ecarx", "watcher " + i3 + ": " + thA);
                            }
                            if (ffVar5 instanceof ff) {
                                ffVar5 = null;
                            }
                            if (k8.e(ffVar5, Boolean.TRUE)) {
                                i2++;
                            }
                        }
                    }
                    if (z || i2 > 0) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    this.z = z2;
                    ffVar2 = Integer.valueOf(Log.i("ReferencePanel.Ecarx", "registerFunctionValueWatcher: all=" + z + ", by function=" + i2 + " of " + size));
                }
            } catch (Throwable th5) {
                ffVar2 = new ff(th5);
            }
            Throwable thA3 = gf.a(ffVar2);
            if (thA3 != null) {
                Log.w("ReferencePanel.Ecarx", "value watcher", thA3);
            }
        }
    }
