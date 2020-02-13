package com.joshua.r0th.jentikrumah;

public class Upload_verif {
    private String mName;
    private String mImageurl;

    public Upload_verif(){

    }
    public Upload_verif(String nama, String Imageurl){
        if (nama.trim().equals("")){
            nama = "tidak ada nama";
        }
        mName = nama;
        mImageurl = Imageurl;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmImageurl() {
        return mImageurl;
    }

    public void setmImageurl(String mImageurl) {
        this.mImageurl = mImageurl;
    }
}
