package com.joshua.r0th.jentikrumah.ui.pantauan;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.util.Log;
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
import com.joshua.r0th.jentikrumah.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class    PantauanFragment extends Fragment  {
EditText tmpnganrumah,tmpunganluar,tmpungandalam,jentikluar,jentikdalam,total_satu_input,total_keseluruhan;
TextView date,namauser,textwarning,textwarningdua,textwarningtiga,textwarning_input;
Button addData;
FirebaseDatabase database;
DatabaseReference myRef;
FirebaseAuth fAuth;
FirebaseFirestore fStore;
String userId;
String myref2;
private static final String TAG = "MyActivity";
Query mPostReference;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_pantauan, container, false);
        tmpunganluar = root.findViewById(R.id.Btampunaganluar);
        textwarningtiga = root.findViewById(R.id.text_warning3);
        textwarning_input = root.findViewById(R.id.text_warning_input);
        textwarningdua = root.findViewById(R.id.text_warning2);
        textwarning = root.findViewById(R.id.text_warning);
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
                cek2();
            }
        });

        insertData();
        //
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

                 //jadiin int dl baru convert ke if
                 //TimeStap
                long mDateTime = 9999999999999L -System.currentTimeMillis();
                String mOrderTime = String.valueOf(mDateTime);
                if(TextUtils.isEmpty(tmpLuar)){
                    tmpunganluar.setError("Tampungan Luar Rumah tidak boleh kosong !.");
                    return;
                }
                if(TextUtils.isEmpty(tmpDalam)){
                    tmpungandalam.setError("Tampungan Dalam Rumah tidak boleh kosong !.");
                    return;
                }
                if(TextUtils.isEmpty(JentikLuar)){
                    tmpunganluar.setError("Jentik Luar tidak boleh kosong !.");
                    return;
                }
                if(TextUtils.isEmpty(JentikDalam)){
                    jentikdalam.setError("Jentik Dalam tidak boleh kosong kosong !.");
                    return;
                }
                int jumlahjntkluar = Integer.parseInt(JentikLuar);
                int jumlahjntkdlm = Integer.parseInt(JentikDalam);
                int tempatLuar = Integer.parseInt(tmpLuar);
                int tempatDalam = Integer.parseInt(tmpDalam);
                if (jumlahjntkluar > 99){
                    jentikdalam.setError("Jentik Luar tidak boleh Lebih dari 99 !.");
                    return;
                }
                if (jumlahjntkdlm > 99){
                    jentikdalam.setError("Jentik Dalam tidak boleh Lebih dari 99 !.");
                    return;
                }if (tempatLuar > 99){
                    jentikdalam.setError("Tampungan Dalam tidak boleh Lebih dari 99 !.");
                    return;
                }

                if (tempatDalam > 99){
                    jentikdalam.setError("Tampungan Luar tidak boleh Lebih dari 99 !.");
                    return;
                }


                int total_satu_input2 = jumlahjntkluar + jumlahjntkdlm;
                int total_semua = 0;

                    data_item data_item1 = new data_item(nama, Date, tmpLuar, tmpDalam, JentikLuar, JentikDalam,total_satu_input2);
                    myRef.child(mOrderTime).setValue(data_item1).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getContext(), "Berhasil menambah Data Pantauan", Toast.LENGTH_SHORT).show();
                            tmpunganluar.setText("");
                            tmpungandalam.setText("");
                            jentikluar.setText("");
                            jentikdalam.setText("");
                            //Menghilangkan fungsi dari Edit Text
                            tmpunganluar.setEnabled(false);
                            tmpungandalam.setEnabled(false);
                            jentikluar.setEnabled(false);
                            jentikdalam.setEnabled(false);

                            //Menghilangkan Button dan memunculkan warning Text
                            textwarningtiga.setVisibility(View.VISIBLE);
                            textwarningdua.setVisibility(View.VISIBLE);
                            textwarning.setVisibility(View.VISIBLE);
                            addData.setVisibility(View.GONE);
                            addData.setEnabled(false);

                            input_delay();
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
    public void input_delay(){
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("input_delay");
        String nama = namauser.getText().toString();
        long mDateTime = System.currentTimeMillis();
        String mOrderTime = String.valueOf(mDateTime);
        long waktu_hit = Long.parseLong(mOrderTime);
        long waktu2 = waktu_hit + DateUtils.DAY_IN_MILLIS + DateUtils.DAY_IN_MILLIS +DateUtils.DAY_IN_MILLIS+DateUtils.DAY_IN_MILLIS+DateUtils.DAY_IN_MILLIS+DateUtils.DAY_IN_MILLIS+DateUtils.DAY_IN_MILLIS;
        String waktu3 = String.valueOf(waktu2);
        waktu_delay delay = new waktu_delay(mOrderTime,waktu3,nama);
        myRef.child(mOrderTime).setValue(delay).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getContext(), "Berhasil", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void cek2(){

        final DatabaseReference myRef2;
        database = FirebaseDatabase.getInstance();
        myRef2 = database.getReference("input_delay");
        final String nama = namauser.getText().toString();
        final Query query = myRef2.orderByChild("namainput_delay").equalTo(nama);

        query.addValueEventListener(new ValueEventListener() {
        long waktuSatu = 0;
        long waktuDepan = 1;
            long mDateTime = System.currentTimeMillis();
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String waktu_sekarang =  postSnapshot.child("waktu_sekarang").getValue(String.class);
                    String waktu_yangdatang =  postSnapshot.child("waktu_seminggu").getValue(String.class);
                    long waktu1 = Long.parseLong(waktu_sekarang);
                    long waktu2 = Long.parseLong(waktu_yangdatang);

                    waktuSatu = waktuSatu + waktu1;
                    waktuDepan = waktuDepan + waktu2;

                }
                Log.i(TAG, String.valueOf(waktuSatu));
                Log.i(TAG, String.valueOf(waktuDepan));
                Log.i(TAG, String.valueOf(mDateTime));
                if (mDateTime > waktuDepan){
                        Log.i(TAG, "pass");
                    //Menghilangkan fungsi dari Edit Text
                    tmpunganluar.setEnabled(true);
                    tmpungandalam.setEnabled(true);
                    jentikluar.setEnabled(true);
                    jentikdalam.setEnabled(true);

                    //Menghilangkan Button dan memunculkan warning Text
                    textwarningtiga.setVisibility(View.GONE);
                    textwarningdua.setVisibility(View.GONE);
                    textwarning.setVisibility(View.GONE);
                    addData.setVisibility(View.VISIBLE);
                    addData.setEnabled(true);
                    String nama2 = namauser.getText().toString();



                } else if (waktuDepan > mDateTime){
                    Log.i(TAG, "pass 2");
                    long mDateTime = System.currentTimeMillis();
                    String mOrderTime = String.valueOf(mDateTime);
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
                    String waktudepan = formatter.format(new Date(waktuDepan));
                    String waktusatu = formatter.format(new Date(waktuSatu));
                    textwarning.setText(waktudepan);
                    textwarning_input.setText(waktusatu);
                    textwarning_input.setVisibility(View.VISIBLE);
                    //Menghilangkan fungsi dari Edit Text
                    tmpunganluar.setEnabled(false);
                    tmpungandalam.setEnabled(false);
                    jentikluar.setEnabled(false);
                    jentikdalam.setEnabled(false);

                    //Menghilangkan Button dan memunculkan warning Text
                    textwarningtiga.setVisibility(View.VISIBLE);
                    textwarningdua.setVisibility(View.VISIBLE);
                    textwarning.setVisibility(View.VISIBLE);
                    addData.setVisibility(View.GONE);
                    addData.setEnabled(false);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    public void remove_waktu(){
        final String nama = namauser.getText().toString();
        final DatabaseReference myRef3;
        database = FirebaseDatabase.getInstance();
        myRef3 = database.getReference("input_delay");
        Query query1 = myRef3.orderByChild("namainput_delay").equalTo(nama);
        query1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    postSnapshot.getRef().removeValue();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}