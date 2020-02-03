package com.joshua.r0th.jentikrumah;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    EditText username,email,password,notelp,alamat1;
    Button daftar;
    FirebaseAuth fAuth;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        notelp = findViewById(R.id.notelp);
        alamat1 = findViewById(R.id.alamat1);

        daftar = findViewById(R.id.daftar);
        fAuth = FirebaseAuth.getInstance();
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


                            Toast.makeText(RegisterActivity.this, " BERHASIL MENDAFTAR", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));

                        } else {
                            Toast.makeText(RegisterActivity.this, " BERHASIL MENDAFTAR"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
