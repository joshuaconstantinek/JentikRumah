package com.joshua.r0th.jentikrumah;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.joshua.r0th.jentikrumah.R;

public class forgot_password extends AppCompatActivity {
    private EditText inputEmail;

        private Button btnReset, btnBack;

        private FirebaseAuth auth;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_forgotpassword);

            inputEmail = (EditText) findViewById(R.id.emailforgot);

            btnReset = (Button) findViewById(R.id.btnforgot);


            auth = FirebaseAuth.getInstance();


            btnReset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String email = inputEmail.getText().toString().trim();

                    if (TextUtils.isEmpty(email)) {
                        Toast.makeText(getApplication(), "Masukan Email yang sudah terdaftar !", Toast.LENGTH_SHORT).show();
                        return;
                    }



                    auth.sendPasswordResetEmail(email)

                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(), "Kami Telah Mengirimkan Anda Intruksi Untuk Me-Reset Password", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Gagal Untuk mengirim email", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                }
            });
        }

}