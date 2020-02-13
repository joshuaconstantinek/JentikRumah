package com.joshua.r0th.jentikrumah.ui.verifikasi;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class data_model_verifikasi extends RecyclerView.Adapter {
    String nama;
    String data;


    public data_model_verifikasi(FirestoreRecyclerOptions<data_model_verifikasi> options){

    }

    public data_model_verifikasi(String nama, String data) {
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

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
