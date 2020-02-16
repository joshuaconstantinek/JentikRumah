package com.joshua.r0th.jentikrumah.ui.verifikasi;

import android.content.Context;
import android.media.Image;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.joshua.r0th.jentikrumah.R;
import com.joshua.r0th.jentikrumah.Upload_verif;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class data_adapter extends RecyclerView.Adapter<data_adapter.ImageViewHolder> {
private Context mContext;
private List<Upload_verif> mUpload;
private OnItemClickListener mlistener;

public data_adapter(Context context, List<Upload_verif> Uploads){
    mContext = context;
    mUpload = Uploads;

}
    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.display_data_verif, parent, false);


        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
    Upload_verif uploadCurrent = mUpload.get(position);
    holder.txtnama.setText(uploadCurrent.getmName());
    holder.status.setText(uploadCurrent.getMstatus());
        Picasso.get().load(uploadCurrent.getmImageurl()).resize(250, 250).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return mUpload.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
        public TextView txtnama,status;
        public ImageView img;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.data_verif_img);
            txtnama = itemView.findViewById(R.id.nama_verif);
            status = itemView.findViewById(R.id.status_verif_tampil);

            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mlistener != null){
                int position  = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION){
                    mlistener.OnItemClick(position);
                }
            }
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Pilih Menu");
            MenuItem Update = menu.add(Menu.NONE,1,1 , "Update");
            MenuItem delete = menu.add(Menu.NONE,2,2, "Delete");

            Update.setOnMenuItemClickListener(this);
            delete.setOnMenuItemClickListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            if (mlistener != null){
                int position  = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION){
                    switch (item.getItemId()){
                        case 1:
                            mlistener.updateclick(position);
                        case 2:
                            mlistener.deletclick(position);
                            return true;
                    }
                }
            }
            return false;
        }
    }

        public interface OnItemClickListener{
    void OnItemClick(int position);

    void updateclick(int position);

    void deletclick(int position);

        }
        public void setOnItemClickListener(OnItemClickListener listener){
        mlistener = listener;
        }
}
