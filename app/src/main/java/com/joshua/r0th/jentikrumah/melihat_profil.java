package com.joshua.r0th.jentikrumah;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
import com.joshua.r0th.jentikrumah.ui.profile.ProfileFragment;

public class melihat_profil extends AppCompatActivity {
    TextView username;
    TextView status;
    TextView Email;
    TextView Notelp;
    TextView alamatrumah;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    DatabaseReference myref;
    String userId;
    Button buttoncontoh;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_profile);
        username = findViewById(R.id.profile1);
        Email = findViewById(R.id.email);
        status = findViewById(R.id.status_profile);
        Notelp = findViewById(R.id.notelp);
        alamatrumah = findViewById(R.id.Alamat);
        myref = FirebaseDatabase.getInstance().getReference("data_verifikasi");
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        buttoncontoh = findViewById(R.id.buttoncontohverif);
        userId = fAuth.getCurrentUser().getUid();
        //mengambil data dari firestore sesuai dengan userID yang telah di daftarkan / yang sedang berjalan
        DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                username.setText(documentSnapshot.getString("Username"));
                Email.setText(documentSnapshot.getString("Email"));
                Notelp.setText(documentSnapshot.getString("Telepon"));
                alamatrumah.setText(documentSnapshot.getString("Alamat"));
                final String username2 = username.getText().toString().trim();

                //Mencari query sesuai dengan nama yang sudah ada untuk di cek apakah user sudah verifikasi KTP atau belom
                Query query=myref.orderByChild("mName").equalTo(username2);

                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Mengambil data yang ada sesuai dengan pencarian Query di atas
                        for (DataSnapshot data:dataSnapshot.getChildren()){


                            Upload_verif models=data.getValue(Upload_verif.class);
                            status.setText(models.getMstatus().trim());
                            final String status2 = status.getText().toString().trim();
                            //membuat kondisi dimana jika status belum verif makan button verifikasi akan ada
                            //sebalik nya jika kondisi sudah verif atau else makan tombol hilang
                            if (status2.equals("Belum Terverifikasi")){
                                buttoncontoh.setVisibility(View.VISIBLE);
                            }else{
                                buttoncontoh.setVisibility(View.GONE);
                            }

                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });
        //jika button verifikasi di tekan maka akan teralifkan ke class verif kt user
        buttoncontoh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), verif_ktp_user.class));
            }
        });


    }

}
