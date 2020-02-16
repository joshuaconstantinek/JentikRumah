package com.joshua.r0th.jentikrumah;

import com.google.firebase.database.Exclude;

public class Upload_verif {
    private String mName;
    private String mImageurl;
    private String mstatus;
    private String mkey ;
    public Upload_verif(){

    }
    public Upload_verif(String nama,String status, String Imageurl){
        if (nama.trim().equals("")){
            nama = "tidak ada nama";
        }
        mName = nama;
        mImageurl = Imageurl;
        mstatus = status;
    }

    public String getmName() {
        return mName;
    }

    public String getMstatus() {
        return mstatus;
    }

    public void setMstatus(String mstatus) {
        this.mstatus = mstatus;
    }

    @Exclude
    public String getMkey() {
        return mkey;
    }
    @Exclude
    public void setMkey(String mkey) {
        this.mkey = mkey;
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
