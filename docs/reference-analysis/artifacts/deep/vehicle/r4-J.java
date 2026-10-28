    public final boolean J(Integer num) {
        if (s(num)) {
            return false;
        }
        Integer numJ = l() ? j(553845760) : k(553845760, 4);
        Boolean bool = null;
        if (k8.e(this.a.getSharedPreferences("panel", 0).getString("vehicle", null), "livan_x6")) {
            if (numJ != null && numJ.intValue() == 1) {
                this.u = true;
            }
            if (numJ != null && numJ.intValue() == 1) {
                bool = Boolean.TRUE;
            } else if (numJ != null && numJ.intValue() == 0 && this.u) {
                bool = Boolean.FALSE;
            }
        } else if (numJ != null && numJ.intValue() == 0) {
            bool = Boolean.FALSE;
        } else if (numJ != null && numJ.intValue() == 1) {
            bool = Boolean.TRUE;
        }
        return bool != null ? bool.booleanValue() : this.r;
    }
