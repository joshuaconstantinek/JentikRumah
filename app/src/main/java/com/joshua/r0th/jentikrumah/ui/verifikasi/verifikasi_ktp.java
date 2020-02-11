package com.joshua.r0th.jentikrumah.ui.verifikasi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.joshua.r0th.jentikrumah.R;

public class verifikasi_ktp extends Fragment {
private FirebaseFirestore db = FirebaseFirestore.getInstance();
private CollectionReference statusref = db.collection("contoh");
private data_model_verifikasi adapter;
    FirebaseAuth fAuth;
    String userId;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_verifikasi_admin, container, false);
        fAuth = FirebaseAuth.getInstance();
        userId = fAuth.getCurrentUser().getUid();

        Query query = statusref.orderBy("status", Query.Direction.ASCENDING);
        FirestoreRecyclerOptions<data_model_verifikasi> options = new FirestoreRecyclerOptions.Builder<data_model_verifikasi>()
                .setQuery(query, data_model_verifikasi.class)
                .build();

        adapter = new data_model_verifikasi(options);
        RecyclerView recyclerView = root.findViewById(R.id.rec_verif_ktp);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

    return root;
    }
}
