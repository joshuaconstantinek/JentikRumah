package com.joshua.r0th.jentikrumah.ui.pantauan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
import com.joshua.r0th.jentikrumah.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class    PantauanFragment extends Fragment  {
EditText tmpnganrumah,tmpunganluar,tmpungandalam,jentikluar,jentikdalam,total_satu_input,total_keseluruhan;
TextView date,namauser,setnamauser;
Button addData;
FirebaseDatabase database;
DatabaseReference myRef;
FirebaseAuth fAuth;
FirebaseFirestore fStore;
String userId;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_pantauan, container, false);
        tmpunganluar = root.findViewById(R.id.Btampunaganluar);
        tmpungandalam = root.findViewById(R.id.Ctampungandalam);
        jentikluar = root.findViewById(R.id.Djentikluar);
        jentikdalam = root.findViewById(R.id.Ejentikdalam);
        addData = root.findViewById(R.id.tambahdata);
        date = root.findViewById(R.id.Adate);
        namauser = root.findViewById(R.id.namauser);
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c);
        date.setText(formattedDate);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = fAuth.getCurrentUser().getUid();
        DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                namauser.setText(documentSnapshot.getString("Username"));
            }
        });
        insertData();
        return root;
    }

    public void insertData(){
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Data");

        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 String nama = namauser.getText().toString();
                 String Date = date.getText().toString();
                 String tmpLuar= tmpunganluar.getText().toString();
                 String tmpDalam= tmpungandalam.getText().toString();
                 String JentikLuar= jentikluar.getText().toString();
                 String JentikDalam= jentikdalam.getText().toString();
                int jumlahjntkluar = Integer.parseInt(JentikLuar);
                int jumlahjntkdlm = Integer.parseInt(JentikDalam);
                 int total_satu_input2 = jumlahjntkluar + jumlahjntkdlm;
                 //TimeStap
                long mDateTime = 9999999999999L -System.currentTimeMillis();
                String mOrderTime = String.valueOf(mDateTime);

                    data_item data_item1 = new data_item(nama, Date, tmpLuar, tmpDalam, JentikLuar, JentikDalam,total_satu_input2);
                    myRef.child(mOrderTime).setValue(data_item1).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getContext(), "Berhasil menambah Data Pantauan", Toast.LENGTH_SHORT).show();
                            tmpunganluar.setText("");
                            tmpungandalam.setText("");
                            jentikluar.setText("");
                            jentikdalam.setText("");
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(), "Gagal menambah Data Pantauan", Toast.LENGTH_SHORT).show();
                        }
                    });

            }
        });
    }
}