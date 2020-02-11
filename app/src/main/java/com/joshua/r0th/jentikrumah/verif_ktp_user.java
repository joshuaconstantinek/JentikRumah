package com.joshua.r0th.jentikrumah;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import org.w3c.dom.Text;

public class verif_ktp_user extends AppCompatActivity {
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;
    TextView nama;
    EditText data;
    Button send;
    FirebaseDatabase database;
    DatabaseReference myRef;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verfi_ktp_user);
        nama = findViewById(R.id.namauserverifktp);
        data = findViewById(R.id.verifcontoh);
        send = findViewById(R.id.btnsendverifuser);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = fAuth.getCurrentUser().getUid();
        DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                nama.setText(documentSnapshot.getString("Username"));

            }
        });

        sendData();
    }
    public void sendData(){
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Data_verif");

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama1 = nama.getText().toString();
                String data1 = data.getText().toString();
                long mDateTime = 9999999999999L -System.currentTimeMillis();
                String mOrderTime = String.valueOf(mDateTime);
                data_verif data_verif1 = new data_verif(nama1,data1);
                myRef.child(mOrderTime).setValue(data_verif1).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(),"Berhasil menambah Data verif",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Gagal menambah Data verif",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
