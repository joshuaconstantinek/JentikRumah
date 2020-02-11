package com.joshua.r0th.jentikrumah;

public class data_verif {
    String nama;
    String data;

    public data_verif(){

    }

    public data_verif(String nama, String data) {
        this.nama = nama;
        this.data = data;
    }


    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
