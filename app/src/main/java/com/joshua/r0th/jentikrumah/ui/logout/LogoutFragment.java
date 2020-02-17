package com.joshua.r0th.jentikrumah.ui.logout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.firebase.auth.FirebaseAuth;
import com.joshua.r0th.jentikrumah.LoginActivity;
import com.joshua.r0th.jentikrumah.MainActivity;
import com.joshua.r0th.jentikrumah.R;

public class LogoutFragment extends Fragment {
FirebaseAuth fAuth;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_send, container, false);
        fAuth = FirebaseAuth.getInstance();
showDialog();
        return root;
    }


    private void showDialog(){

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());

        // set title dialog
        alertDialogBuilder.setTitle("Logout dari Aplikasi?");

        // set pesan dari dialog
        alertDialogBuilder
                .setMessage("Apa Ingin Logout ?")
                .setIcon(R.mipmap.ic_launcher_round)
                .setCancelable(false)
                .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        FirebaseAuth.getInstance().signOut();//logout
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Tidak",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // jika tombol ini diklik, akan menutup dialog
                        // dan tidak terjadi apa2
                        dialog.dismiss();
                    }
                });

        // membuat alert dialog dari builder
        AlertDialog alertDialog = alertDialogBuilder.create();

        // menampilkan alert dialog
        alertDialog.show();
    }
}