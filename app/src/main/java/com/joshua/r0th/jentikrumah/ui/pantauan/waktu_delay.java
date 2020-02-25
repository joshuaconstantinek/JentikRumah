package com.joshua.r0th.jentikrumah.ui.pantauan;

public class waktu_delay {
    String waktu_sekarang;
    String  waktu_seminggu;
    String namainput_delay;

    public waktu_delay(String waktu_sekarang, String waktu_seminggu, String namainput_delay) {
        this.waktu_sekarang = waktu_sekarang;
        this.waktu_seminggu = waktu_seminggu;
        this.namainput_delay = namainput_delay;
    }

    public String getWaktu_sekarang() {
        return waktu_sekarang;
    }

    public void setWaktu_sekarang(String waktu_sekarang) {
        this.waktu_sekarang = waktu_sekarang;
    }

    public String getWaktu_seminggu() {
        return waktu_seminggu;
    }

    public void setWaktu_seminggu(String waktu_seminggu) {
        this.waktu_seminggu = waktu_seminggu;
    }

    public String getNamainput_delay() {
        return namainput_delay;
    }

    public void setNamainput_delay(String namainput_delay) {
        this.namainput_delay = namainput_delay;
    }
}
