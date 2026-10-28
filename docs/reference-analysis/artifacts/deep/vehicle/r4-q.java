    public final v2 q() {
        String str;
        List list = z2.a;
        List listI0 = j3.i0(1, 4);
        ArrayList arrayList = new ArrayList();
        Iterator it = listI0.iterator();
        while (true) {
            yc ycVar = null;
            if (!it.hasNext()) {
                break;
            }
            int iIntValue = ((Number) it.next()).intValue();
            List list2 = z2.a;
            Float fJ = z2.j(i(268828928, iIntValue));
            if (fJ != null) {
                ycVar = new yc(Integer.valueOf(iIntValue), Float.valueOf(fJ.floatValue()));
            }
            if (ycVar != null) {
                arrayList.add(ycVar);
            }
        }
        Map mapL0 = hb.l0(arrayList);
        Integer numH = this.o.h(268566784);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Integer numH2 = this.o.h(268501248);
        if (numH2 != null) {
            linkedHashMap.put(268501248, Integer.valueOf(numH2.intValue()));
        }
        Integer numH3 = this.o.h(268501760);
        if (numH3 != null) {
            linkedHashMap.put(268501760, Integer.valueOf(numH3.intValue()));
        }
        Integer numH4 = this.o.h(268502016);
        if (numH4 != null) {
            linkedHashMap.put(268502016, Integer.valueOf(numH4.intValue()));
        }
        Integer numH5 = this.o.h(268501504);
        if (numH5 != null) {
            linkedHashMap.put(268501504, Integer.valueOf(numH5.intValue()));
        }
        if (numH != null) {
            linkedHashMap.put(268566784, Integer.valueOf(numH.intValue()));
        }
        Integer numH6 = this.o.h(268632320);
        if (numH6 != null) {
            linkedHashMap.put(268632320, Integer.valueOf(numH6.intValue()));
        }
        Integer numH7 = this.o.h(268894464);
        if (numH7 != null) {
            linkedHashMap.put(268894464, Integer.valueOf(numH7.intValue()));
        }
        Integer numH8 = this.o.h(268697856);
        if (numH8 != null) {
            linkedHashMap.put(268697856, Integer.valueOf(numH8.intValue()));
        }
        Integer numH9 = this.o.h(268698368);
        if (numH9 != null) {
            linkedHashMap.put(268698368, Integer.valueOf(numH9.intValue()));
        }
        Integer numH10 = this.o.h(269747456);
        if (numH10 != null) {
            linkedHashMap.put(269747456, Integer.valueOf(numH10.intValue()));
        }
        this.a.getSharedPreferences("panel", 0).getString("vehicle", null);
        Integer numH11 = this.o.h(269747200);
        if (numH11 != null) {
            linkedHashMap.put(269747200, Integer.valueOf(numH11.intValue()));
        }
        Integer numJ = j(269025536);
        if (numJ != null) {
            linkedHashMap.put(269025536, Integer.valueOf(numJ.intValue()));
        }
        Integer numH12 = this.o.h(268829952);
        if (numH12 != null) {
            linkedHashMap.put(268829952, Integer.valueOf(numH12.intValue()));
        }
        Integer numJ2 = j(268830208);
        if (numJ2 != null && numJ2.intValue() == 268830209) {
            str = "°C";
        } else {
            str = (numJ2 != null && numJ2.intValue() == 268830210) ? "°F" : "";
        }
        String str2 = str;
        Set setEntrySet = z2.e().entrySet();
        setEntrySet.getClass();
        String strY0 = i3.y0(setEntrySet, "; ", new a(linkedHashMap), 30);
        if (!strY0.equals(this.q)) {
            this.q = strY0;
            K(si.a("polling_0", strY0));
        }
        return new v2(mapL0, h(268829440), h(268829184), h(268829696), str2, linkedHashMap);
    }
