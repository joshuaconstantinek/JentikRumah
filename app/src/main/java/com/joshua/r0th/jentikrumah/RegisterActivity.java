package com.joshua.r0th.jentikrumah;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText username,email,password,notelp,alamat1,tipelogin;
    Button daftar;
    Button Login;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        notelp = findViewById(R.id.notelp);
        alamat1 = findViewById(R.id.alamat1);
        Login = findViewById(R.id.backtologin);
        daftar = findViewById(R.id.daftar);
        tipelogin = findViewById(R.id.tipeLogin);
        fAuth = FirebaseAuth.getInstance();
        String username1 = username.getText().toString();
        String email1 = email.getText().toString();
        String password1 = password.getText().toString();
        String notelp1 = notelp.getText().toString();
        String alamat2 = alamat1.getText().toString();
        fStore = FirebaseFirestore.getInstance();
        if (fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = email.getText().toString().trim();
                String Password = password.getText().toString().trim();
                if (TextUtils.isEmpty(Email)){
                    email.setError(" EMAIL DI BUTUHKAN !");
                    return;

                }
                if (TextUtils.isEmpty(Password)){
                    password.setError(" PASSWORD DI BUTUHKAN !");
                    return;
                }
                if (password.length() < 6){
                    password.setError(" PASSWORD HARUS LEBIH DARI 6 KARAKTER !");
                    return;
                }
                fAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("users").document(userID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("Username",username.getText().toString());
                            user.put("Email",email.getText().toString());
                            user.put("Telepon",notelp.getText().toString());
                            user.put("Alamat",alamat1.getText().toString());
                            user.put("Tipe",tipelogin.getText().toString());
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "onSuccess: User profile di buat untuk "+ userID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "onFailure: " + e.toString());
                                }
                            });
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));

                        } else {
                            Toast.makeText(RegisterActivity.this, " BERHASIL MENDAFTAR"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
                    Login.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                        }
                    });
    }
}
