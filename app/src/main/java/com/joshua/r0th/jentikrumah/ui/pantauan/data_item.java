package com.joshua.r0th.jentikrumah.ui.pantauan;

public class data_item {
    String Adate;
    String Btampunganrumah;
    String Ctampunganluar;
    String Dtampungandalam;
    String Ejentikliuar;
    String Fjentikdalam;

    public data_item(){

    }

    public data_item(String adate, String btampunganrumah, String ctampunganluar, String dtampungandalam, String ejentikliuar, String fjentikdalam) {
        Adate = adate;
        Btampunganrumah = btampunganrumah;
        Ctampunganluar = ctampunganluar;
        Dtampungandalam = dtampungandalam;
        Ejentikliuar = ejentikliuar;
        Fjentikdalam = fjentikdalam;
    }

    public String getAdate() {
        return Adate;
    }

    public void setAdate(String adate) {
        Adate = adate;
    }

    public String getBtampunganrumah() {
        return Btampunganrumah;
    }

    public void setBtampunganrumah(String btampunganrumah) {
        Btampunganrumah = btampunganrumah;
    }

    public String getCtampunganluar() {
        return Ctampunganluar;
    }

    public void setCtampunganluar(String ctampunganluar) {
        Ctampunganluar = ctampunganluar;
    }

    public String getDtampungandalam() {
        return Dtampungandalam;
    }

    public void setDtampungandalam(String dtampungandalam) {
        Dtampungandalam = dtampungandalam;
    }

    public String getEjentikliuar() {
        return Ejentikliuar;
    }

    public void setEjentikliuar(String ejentikliuar) {
        Ejentikliuar = ejentikliuar;
    }

    public String getFjentikdalam() {
        return Fjentikdalam;
    }

    public void setFjentikdalam(String fjentikdalam) {
        Fjentikdalam = fjentikdalam;
    }
}
