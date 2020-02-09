package com.joshua.r0th.jentikrumah.ui.pantauan;

public class data_item {
    String Anama;
    String Bdate;
    String Ctampunganrumah;
    String Dtampunganluar;
    String Etampungandalam;
    String Fjentikliuar;
    String Gjentikdalam;

    public data_item(){

    }

    public data_item(String anama,String bdate, String ctampunganrumah, String dtampunganluar, String etampungandalam, String fjentikliuar, String gjentikdalam) {
        Anama = anama;
        Bdate = bdate;
        Ctampunganrumah = ctampunganrumah;
        Dtampunganluar = dtampunganluar;
        Etampungandalam = etampungandalam;
        Fjentikliuar = fjentikliuar;
        Gjentikdalam = gjentikdalam;
    }

    public String getAnama() {
        return Anama;
    }

    public void setAnama(String anama) {
        Anama = anama;
    }

    public String getBdate() {
        return Bdate;
    }

    public void setBdate(String bdate) {
        Bdate = bdate;
    }

    public String getCtampunganrumah() {
        return Ctampunganrumah;
    }

    public void setCtampunganrumah(String ctampunganrumah) {
        Ctampunganrumah = ctampunganrumah;
    }

    public String getDtampunganluar() {
        return Dtampunganluar;
    }

    public void setDtampunganluar(String dtampunganluar) {
        Dtampunganluar = dtampunganluar;
    }

    public String getEtampungandalam() {
        return Etampungandalam;
    }

    public void setEtampungandalam(String etampungandalam) {
        Etampungandalam = etampungandalam;
    }

    public String getFjentikliuar() {
        return Fjentikliuar;
    }

    public void setFjentikliuar(String fjentikliuar) {
        Fjentikliuar = fjentikliuar;
    }

    public String getGjentikdalam() {
        return Gjentikdalam;
    }

    public void setGjentikdalam(String gjentikdalam) {
        Gjentikdalam = gjentikdalam;
    }
}
