package com.joshua.r0th.jentikrumah.ui.verifikasi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.joshua.r0th.jentikrumah.R;

import org.w3c.dom.Text;

public class data_adapter extends FirestoreRecyclerAdapter<data_model_verifikasi, data_adapter.dataholder> {
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public data_adapter(@NonNull FirestoreRecyclerOptions<data_model_verifikasi> options) {
        super(options);

    }

    @Override
    protected void onBindViewHolder(@NonNull dataholder dataholder, int i, @NonNull data_model_verifikasi data_model_verifikasi) {
        dataholder.nama.setText(data_model_verifikasi.getNama());
        dataholder.status.setText(data_model_verifikasi.getStatus());
    }

    @NonNull
    @Override
    public dataholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_item_verifikasi, parent, false);
        return new dataholder(v);

    }

    class dataholder extends RecyclerView.ViewHolder{
            TextView nama,status;
            public dataholder(@NonNull View itemView) {
                super(itemView);
                nama = itemView.findViewById(R.id.namauserverif);
                status = itemView.findViewById(R.id.status_user);
            }
        }


}
