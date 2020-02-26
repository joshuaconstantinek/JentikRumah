package com.joshua.r0th.jentikrumah.ui.riwayat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.joshua.r0th.jentikrumah.LoginActivity;
import com.joshua.r0th.jentikrumah.R;
import com.joshua.r0th.jentikrumah.ui.pantauan.data_item;

import java.util.ArrayList;
import java.util.List;

public class RiwayatFragment extends Fragment {
private RecyclerView recyclerView;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
private itemAdapter adapter;
private List<data_item> items;
private DatabaseReference reference;
private FirebaseDatabase database;
public DatabaseReference myRef2;
    String userId;
FirebaseRecyclerOptions<data_item> options;
FirebaseRecyclerAdapter<data_item, viewHolder> adapter2;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_riwayat, container, false);
            recyclerView = root.findViewById(R.id.rec1);
            recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Data");

        setHasOptionsMenu(true);
        showtask();

                return root;
    }

    public void showtask(){
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = fAuth.getCurrentUser().getUid();




        DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                String namasearch = documentSnapshot.getString("Username");
                Query query = reference.orderByChild("anama").equalTo(namasearch);
                options = new FirebaseRecyclerOptions.Builder<data_item>()
                        .setQuery(query, data_item.class)
                        .build();

                query.addValueEventListener(new ValueEventListener() {
                    int sum = 0;
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                            sum = sum + postSnapshot.child("gtotal_satu").getValue(Integer.class);


                        }
                        if (sum > 30){
                            if (getActivity() != null) {
                                showDialog();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                });

                adapter2 = new FirebaseRecyclerAdapter<data_item, viewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull viewHolder viewHolder, int i, @NonNull data_item data_item) {

                        viewHolder.rvnama.setText(data_item.getAnama());
                        viewHolder.rvtanggal.setText(data_item.getBdate());
                        viewHolder.rvtmgluar.setText(data_item.getCtampunganluar());
                        viewHolder.rvtmpgdlm.setText(data_item.getDtampungandalam());
                        viewHolder.jntkluar.setText(data_item.getEjentikliuar());
                        viewHolder.jntkdlm.setText(data_item.getFjentikdalam());
                        viewHolder.rvtotal.setText(Integer.toString(data_item.getGtotal_satu()));


                    }

                    @NonNull
                    @Override
                    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View itemview = LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.display_data, parent, false);
                        return new viewHolder(itemview);
                    }
                };
                adapter2.startListening();
                recyclerView.setAdapter(adapter2);
                adapter2.notifyDataSetChanged();
            }

        });

    }


    @Override
    public boolean onContextItemSelected(@NonNull final MenuItem item) {
        if(item.getTitle().equals("Update")){
            showUpdateDialog(adapter2.getRef(item.getOrder()).getKey(), adapter2.getItem(item.getOrder()));


        } else if(item.getTitle().equals("Delete")){
            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
            alertDialogBuilder.setTitle("Hapus Data ?");

            // set pesan dari dialog
            alertDialogBuilder
                    .setMessage("Menghapus data ini ?")
                    .setIcon(R.mipmap.ic_launcher_round)
                    .setCancelable(false)
                    .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            deleteTask(adapter2.getRef(item.getOrder()).getKey());
                            remove_item();


                        }
                    })
                    .setNegativeButton("Tidak",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            dialog.dismiss();
                        }
                    });

            // membuat alert dialog dari builder
            AlertDialog alertDialog = alertDialogBuilder.create();

            // menampilkan alert dialog
            alertDialog.show();

        }

        return super.onContextItemSelected(item);

    }

    private void deleteTask(String key) {
        reference.child(key).removeValue();

    }

    private void showUpdateDialog(final String key, data_item item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Update");
        builder.setMessage("Silahkan Update Data");

        View updateLayout = LayoutInflater.from(getContext()).inflate(R.layout.custom_layout, null);
        final TextView namauser = updateLayout.findViewById(R.id.namauser);
        final TextView tanggal = updateLayout.findViewById(R.id.Adate);
        final EditText edit_tmpgrmhluar = updateLayout.findViewById(R.id.edit_Ctampunaganluar);
        final EditText edit_tmpgrmhdlm = updateLayout.findViewById(R.id.edit_Dtampungandalam);
        final EditText edit_jntikLuar = updateLayout.findViewById(R.id.edit_Ejentikluar);
        final EditText edit_jntikDalam = updateLayout.findViewById(R.id.edit_Fjentikdalam);
        final TextView total_satu_input = updateLayout.findViewById(R.id.totaljentikEdit);

        namauser.setText(item.getAnama());
        tanggal.setText(item.getBdate());
        edit_tmpgrmhluar.setText(item.getCtampunganluar());
        edit_tmpgrmhdlm.setText(item.getDtampungandalam());
        edit_jntikLuar.setText(item.getEjentikliuar());
        edit_jntikDalam.setText(item.getFjentikdalam());
        total_satu_input.setText(Integer.toString(item.getGtotal_satu()));

        builder.setView(updateLayout);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String nm = namauser.getText().toString();
                String tnggl = tanggal.getText().toString();
                String tpg_luar = edit_tmpgrmhluar.getText().toString();
                String tpg_dlm = edit_tmpgrmhdlm.getText().toString();
                String jntk_luar = edit_jntikLuar.getText().toString();
                String jntk_dalam = edit_jntikDalam.getText().toString();
                int jumlahjntkluar = Integer.parseInt(jntk_luar);
                int jumlahjntkdlm = Integer.parseInt(jntk_dalam);
                int total_satu_input2 = jumlahjntkluar + jumlahjntkdlm;

               data_item daitem3 = new data_item(nm,tnggl,tpg_luar,tpg_dlm,jntk_luar,jntk_dalam,total_satu_input2);
               reference.child(key).setValue(daitem3);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_delete, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.delete_all){
            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
            // set title dialog
            alertDialogBuilder.setTitle("Hapus semua data ?");

            // set pesan dari dialog
            alertDialogBuilder
                    .setMessage("Menghapus semua data ?")
                    .setIcon(R.mipmap.ic_launcher_round)
                    .setCancelable(false)
                    .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            reference.removeValue();
                            if (getActivity() != null ){
                                remove_item();
                            }
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
        return super.onOptionsItemSelected(item);
    }



    private void showDialog(){

        final androidx.appcompat.app.AlertDialog.Builder alertDialogBuilder = new androidx.appcompat.app.AlertDialog.Builder(getActivity());

        // set title dialog
        alertDialogBuilder.setTitle("WARNING !");

        // set pesan dari dialog
        alertDialogBuilder
                .setMessage("Jentik Rumah anda sudah melebihi batas ! Segera Lakukan Fogging")
                .setIcon(R.mipmap.ic_launcher_round)
                .setCancelable(false)
                .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.dismiss();
                    }
                });

        // membuat alert dialog dari builder
        androidx.appcompat.app.AlertDialog alertDialog = alertDialogBuilder.create();

        // menampilkan alert dialog
        alertDialog.show();
    }
    private void remove_item(){
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = fAuth.getCurrentUser().getUid();
        DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                String namacek = documentSnapshot.getString("Username");
                database = FirebaseDatabase.getInstance();
                myRef2 = database.getReference("input_delay");
                final Query query = myRef2.orderByChild("namainput_delay").equalTo(namacek);
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            postSnapshot.getRef().removeValue();

                        }
                        query.removeEventListener(this);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                });

            }
        });

    }
}