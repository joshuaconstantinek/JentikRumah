package com.joshua.r0th.jentikrumah.ui.riwayat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.joshua.r0th.jentikrumah.R;
import com.joshua.r0th.jentikrumah.ui.pantauan.data_item;

import java.util.List;

public class itemAdapter extends RecyclerView.Adapter<itemAdapter.ViewHolder> {
    private Context context;
    private List<data_item> items;

    public itemAdapter(Context context, List<data_item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public itemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.display_data,parent,false);
        return new itemAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull itemAdapter.ViewHolder holder, int position) {
    final data_item item = items.get(position);
    holder.rvtanggal.setText(item.getBdate());
    holder.rvnama.setText(item.getAnama());
    holder.rvtmpgrmh.setText(item.getCtampunganrumah());
    holder.rvtmgluar.setText(item.getDtampunganluar());
    holder.rvtmpgdlm.setText(item.getEtampungandalam());
    holder.jntkluar.setText(item.getFjentikliuar());
    holder.jntkdlm.setText(item.getGjentikdalam());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView rvtanggal,rvnama,rvtmpgrmh,rvtmgluar,rvtmpgdlm,jntkluar,jntkdlm;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rvnama = itemView.findViewById(R.id.nama);
            rvtanggal = itemView.findViewById(R.id.tanggal);
            rvtmpgrmh = itemView.findViewById(R.id.tmpunganrumah);
            rvtmgluar = itemView.findViewById(R.id.tmpunganluarrumah);
            rvtmpgdlm = itemView.findViewById(R.id.tmpungandalamrumah);
            jntkluar = itemView.findViewById(R.id.jentikluar);
            jntkdlm = itemView.findViewById(R.id.jentikdalam);

        }
    }
}
