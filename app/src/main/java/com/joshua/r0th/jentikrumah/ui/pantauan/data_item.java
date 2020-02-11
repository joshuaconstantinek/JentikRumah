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
        this.Anama = anama;
    }

    public String getBdate() {
        return Bdate;
    }

    public void setBdate(String bdate) {
        this.Bdate = bdate;
    }

    public String getCtampunganrumah() {
        return Ctampunganrumah;
    }

    public void setCtampunganrumah(String ctampunganrumah) {
        this.Ctampunganrumah = ctampunganrumah;
    }

    public String getDtampunganluar() {
        return Dtampunganluar;
    }

    public void setDtampunganluar(String dtampunganluar) {
        this.Dtampunganluar = dtampunganluar;
    }

    public String getEtampungandalam() {
        return Etampungandalam;
    }

    public void setEtampungandalam(String etampungandalam) {
        this.Etampungandalam = etampungandalam;
    }

    public String getFjentikliuar() {
        return Fjentikliuar;
    }

    public void setFjentikliuar(String fjentikliuar) {
        this.Fjentikliuar = fjentikliuar;
    }

    public String getGjentikdalam() {
        return Gjentikdalam;
    }

    public void setGjentikdalam(String gjentikdalam) {
        Gjentikdalam = gjentikdalam;
    }

    public int setCtampunganrumah() {
        return 0;
    }

    public int setDtampunganluar() {
        return 0;
    }

    public int setEtampungandalam() {
    return 0;
    }

    public int setFjentikliuar() {
        return 0;
    }

    public int setGjentikdalam() {
        return 0;
    }
}
