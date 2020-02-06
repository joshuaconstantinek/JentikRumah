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
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.joshua.r0th.jentikrumah.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class    PantauanFragment extends Fragment  {
EditText tmpnganrumah,tmpunganluar,tmpungandalam,jentikluar,jentikdalam;
TextView date,namauser,setnamauser;
Button addData;
FirebaseDatabase database;
DatabaseReference myRef;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_pantauan, container, false);
        tmpnganrumah = root.findViewById(R.id.Btampunganrumah);
        tmpunganluar = root.findViewById(R.id.Ctampunaganluar);
        tmpungandalam = root.findViewById(R.id.Dtampungandalam);
        jentikluar = root.findViewById(R.id.Ejentikluar);
        jentikdalam = root.findViewById(R.id.Fjentikdalam);
        addData = root.findViewById(R.id.tambahdata);
        date = root.findViewById(R.id.Adate);
        namauser = root.findViewById(R.id.namauser);
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c);
        date.setText(formattedDate);

       //  = FirebaseDatabase.getInstance();
       //  = database.getReference("message");

       // myRef.setValue("Hello, World!");
        insertData();
        return root;
    }
    public void insertData(){
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Data");

        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 String Date = date.getText().toString();
                 String tmpRumah= tmpnganrumah.getText().toString();
                 String tmpLuar= tmpunganluar.getText().toString();
                 String tmpDalam= tmpungandalam.getText().toString();
                 String JentikLuar= jentikluar.getText().toString();
                 String JentikDalam= jentikdalam.getText().toString();
                 //TimeStap
                long mDateTime = 9999999999999L -System.currentTimeMillis();
                String mOrderTime = String.valueOf(mDateTime);

            data_item data_item1 = new data_item(Date,tmpRumah,tmpLuar,tmpDalam,JentikLuar,JentikDalam);
            myRef.child(mOrderTime).setValue(data_item1).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(getContext(),"Berhasil menambah Data Pantauan",Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getContext(),"Gagal menambah Data Pantauan",Toast.LENGTH_SHORT).show();
                }
            });
            }
        });
    }
}