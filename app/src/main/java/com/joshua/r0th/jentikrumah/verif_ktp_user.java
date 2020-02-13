package com.joshua.r0th.jentikrumah;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class verif_ktp_user extends AppCompatActivity {
    private static final int PICK_IMAGE=1;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;
    TextView nama;
    Button send;
    Button upload;
    ImageView imageView;
    ProgressBar progressBar;
    FirebaseDatabase database;
    DatabaseReference myRef;
    StorageReference mStorageref;
    private StorageTask mUploadTask;

    private Uri mImageUri;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verfi_ktp_user);
        nama = findViewById(R.id.namauserverifktp);
        upload = findViewById(R.id.ktp_img);
        progressBar = findViewById(R.id.progressbar);
        send = findViewById(R.id.btnsendverifuser);
        imageView = findViewById(R.id.imageLoad);
        mStorageref = FirebaseStorage.getInstance().getReference("data_verifikasi");
        myRef = FirebaseDatabase.getInstance().getReference("data_verifikasi");
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
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            openfileChooser();
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUploadTask != null && mUploadTask.isInProgress()){

                    Toast.makeText(getApplicationContext(),"Upload Sedang berjalan !! ",Toast.LENGTH_SHORT).show();
                }else {
                    UploadFile();
                }

            }
        });
        //sendData();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data !=null && data.getData() !=null){
        mImageUri = data.getData();

            Picasso.get()
                    .load(mImageUri)
                    .into(imageView);
        }
    }

    private void openfileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE);

    }
    private String getfileextension(Uri uri){
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
    private void UploadFile(){
        if (mImageUri != null){
            StorageReference fileref = mStorageref.child(System.currentTimeMillis() + "." + getfileextension(mImageUri));
            mUploadTask =  fileref.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    final String downloadUrl = uri.toString();

                    Upload_verif upload = new Upload_verif(nama.getText().toString().trim(), downloadUrl);
                    String UploadId = myRef.push().getKey();
                    myRef.child(UploadId).setValue(upload);
                }

                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(0);
                        }
                    },500);
                    Toast.makeText(getApplicationContext(),"Upload Berhasil ! ",Toast.LENGTH_LONG).show();
                    Upload_verif upload = new Upload_verif(nama.getText().toString().trim(), taskSnapshot.getStorage().getDownloadUrl().toString());
                    String UploadId = myRef.push().getKey();
                    myRef.child(UploadId).setValue(upload);

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(),"Upload Gagal, Cek koneksi anda !",Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                progressBar.setProgress((int) progress);
                }
            });
        }else {
            Toast.makeText(getApplicationContext(),"tidak ada file yang di pilih",Toast.LENGTH_SHORT).show();
        }
    }
}
