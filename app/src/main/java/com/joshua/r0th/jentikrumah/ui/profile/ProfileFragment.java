package com.joshua.r0th.jentikrumah.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.joshua.r0th.jentikrumah.MainActivity;
import com.joshua.r0th.jentikrumah.R;
import com.joshua.r0th.jentikrumah.contoh;

import java.util.concurrent.Executor;

public class ProfileFragment extends Fragment {
TextView username;
TextView Email;
TextView Notelp;
TextView alamatrumah;
FirebaseAuth fAuth;
FirebaseFirestore fStore;
String userId;
Button contoh;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        username = root.findViewById(R.id.profile1);
        Email = root.findViewById(R.id.email);
        Notelp = root.findViewById(R.id.notelp);
        alamatrumah = root.findViewById(R.id.Alamat);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        contoh = root.findViewById(R.id.contoh);
        userId = fAuth.getCurrentUser().getUid();
        contoh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), com.joshua.r0th.jentikrumah.contoh.class));
            }
        });
        return root;
    }
}