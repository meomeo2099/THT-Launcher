    public final boolean y(int i, int i2) {
        List listI0 = j3.i0(Integer.MIN_VALUE, 0, 1);
        if (!listI0.isEmpty()) {
            Iterator it = listI0.iterator();
            while (it.hasNext()) {
                if (x(i, ((Number) it.next()).intValue(), i2)) {
                    return true;
                }
            }
        }
        return false;
    }
