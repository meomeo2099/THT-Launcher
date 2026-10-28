    public final Object b(String str, Class[] clsArr, boolean z, Object... objArr) {
        int iOffset;
        Object obj = this.j;
        if (obj == null) {
            return null;
        }
        try {
            ConcurrentHashMap concurrentHashMap = this.c;
            String str2 = obj.getClass().getName() + "#" + str + "/" + clsArr.length;
            Object obj2 = concurrentHashMap.get(str2);
            if (obj2 == null) {
                Method method = obj.getClass().getMethod(str, (Class[]) Arrays.copyOf(clsArr, clsArr.length));
                Object objPutIfAbsent = concurrentHashMap.putIfAbsent(str2, method);
                obj2 = objPutIfAbsent == null ? method : objPutIfAbsent;
            }
            Method method2 = (Method) obj2;
            int length = objArr.length;
            Object[] objArr2 = new Object[length];
            for (int i = 0; i < length; i++) {
                objArr2[i] = objArr[i];
            }
            if (str.charAt(0) == 's' && length != 0 && (objArr2[0] instanceof Integer) && (iOffset = Guard.INSTANCE.offset(this.a)) != 0) {
                Object obj3 = objArr2[0];
                obj3.getClass();
                objArr2[0] = Integer.valueOf(((Integer) obj3).intValue() + iOffset);
            }
            return method2.invoke(obj, Arrays.copyOf(objArr2, length));
        } catch (InvocationTargetException e) {
            if (!z) {
                return null;
            }
            String string = Arrays.toString(objArr);
            string.getClass();
            Log.w("ReferencePanel.Ecarx", str + string + ": " + e.getTargetException());
            return null;
        } catch (Exception e2) {
            if (!z) {
                return null;
            }
            String string2 = Arrays.toString(objArr);
            string2.getClass();
            Log.w("ReferencePanel.Ecarx", str + string2 + ": " + e2);
            return null;
        }
    }
