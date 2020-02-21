package com.joshua.r0th.jentikrumah.ui.riwayat;

import android.view.ContextMenu;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.joshua.r0th.jentikrumah.R;

public class viewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
    public TextView rvtanggal,rvnama,rvtotal,rvtmgluar,rvtmpgdlm,jntkluar,jntkdlm;
    public viewHolder(@NonNull View itemView) {
        super(itemView);
        rvnama = itemView.findViewById(R.id.nama);
        rvtanggal = itemView.findViewById(R.id.tanggal);
        rvtmgluar = itemView.findViewById(R.id.tmpunganluarrumah);
        rvtmpgdlm = itemView.findViewById(R.id.tmpungandalamrumah);
        jntkluar = itemView.findViewById(R.id.jentikluar);
        jntkdlm = itemView.findViewById(R.id.jentikdalam);
        rvtotal = itemView.findViewById(R.id.totaljentik_tampil);
        itemView.setOnCreateContextMenuListener(this);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
    menu.setHeaderTitle("Pilih Menu");
    menu.add(0,0,getAdapterPosition(), "Update");
    menu.add(0,1,getAdapterPosition(), "Delete");
    }
}
