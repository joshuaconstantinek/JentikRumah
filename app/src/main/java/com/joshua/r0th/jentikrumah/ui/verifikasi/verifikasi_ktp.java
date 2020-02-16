package com.joshua.r0th.jentikrumah.ui.verifikasi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.joshua.r0th.jentikrumah.R;
import com.joshua.r0th.jentikrumah.Upload_verif;
import com.joshua.r0th.jentikrumah.ui.pantauan.data_item;
import com.joshua.r0th.jentikrumah.ui.riwayat.itemAdapter;
import com.joshua.r0th.jentikrumah.ui.riwayat.viewHolder;

import java.util.ArrayList;
import java.util.List;

public class verifikasi_ktp extends Fragment implements data_adapter.OnItemClickListener {
private FirebaseFirestore db = FirebaseFirestore.getInstance();
private data_adapter madapter;

    FirebaseAuth fAuth;
    String userId;
    private RecyclerView recyclerView;
    private DatabaseReference mDatabaseRef;
    private List<Upload_verif> mUpload;
    private FirebaseDatabase database;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_verifikasi_admin, container, false);
        fAuth = FirebaseAuth.getInstance();
        userId = fAuth.getCurrentUser().getUid();
        recyclerView = root.findViewById(R.id.rec_verif_ktp);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        mUpload = new ArrayList<>();
        database = FirebaseDatabase.getInstance();
        setHasOptionsMenu(true);

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("data_verifikasi");
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            for (DataSnapshot postsnapshot : dataSnapshot.getChildren()){
                Upload_verif Uploads = postsnapshot.getValue(Upload_verif.class);
                mUpload.add(Uploads);
            }
                madapter = new data_adapter(getContext(),mUpload);
            recyclerView.setAdapter(madapter);

            madapter.setOnItemClickListener(verifikasi_ktp.this);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    return root;
    }

    @Override
    public void OnItemClick(int position) {
        Toast.makeText(getContext(),"normalClick" + position,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateclick(int position) {
        Toast.makeText(getContext(),"Update " + position,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void deletclick(int position) {
        Toast.makeText(getContext(),"Delete " + position,Toast.LENGTH_SHORT).show();
    }
}
