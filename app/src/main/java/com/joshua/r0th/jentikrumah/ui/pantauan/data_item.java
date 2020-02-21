package com.joshua.r0th.jentikrumah.ui.pantauan;

public class data_item {
    String Anama;
    String Bdate;
    String Ctampunganluar;
    String Dtampungandalam;
    String Ejentikliuar;
    String Fjentikdalam;
    int Gtotal_satu;
    public data_item(){

    }

    public data_item(String anama,String bdate, String ctampunganluar, String dtampungandalam, String ejentikliuar, String fjentikdalam, int gtotal_satu) {
        Anama = anama;
        Bdate = bdate;
        Ctampunganluar = ctampunganluar;
        Dtampungandalam = dtampungandalam;
        Ejentikliuar = ejentikliuar;
        Fjentikdalam = fjentikdalam;
        Gtotal_satu = gtotal_satu;
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

    public int getGtotal_satu() {
        return Gtotal_satu;
    }

    public void setGtotal_satu(int gtotal_satu) {
        Gtotal_satu = gtotal_satu;
    }
}
