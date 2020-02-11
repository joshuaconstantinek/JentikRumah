package com.joshua.r0th.jentikrumah;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.joshua.r0th.jentikrumah.ui.profile.ProfileFragment;

public class contoh extends AppCompatActivity {
    TextView username;
    TextView Email;
    TextView Notelp;
    TextView alamatrumah;
    TextView tipelogin1;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;
    Button buttoncontoh;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_profile);
        username = findViewById(R.id.profile1);
        Email = findViewById(R.id.email);
        Notelp = findViewById(R.id.notelp);
        alamatrumah = findViewById(R.id.Alamat);
        tipelogin1 = findViewById(R.id.tipelogin);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        buttoncontoh = findViewById(R.id.buttoncontohverif);
        userId = fAuth.getCurrentUser().getUid();
        DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                username.setText(documentSnapshot.getString("Username"));
                Email.setText(documentSnapshot.getString("Email"));
                Notelp.setText(documentSnapshot.getString("Telepon"));
                alamatrumah.setText(documentSnapshot.getString("Alamat"));
                tipelogin1.setText(documentSnapshot.getString("Tipe"));
            }
        });
        buttoncontoh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), verif_ktp_user.class));
            }
        });
    }


}
