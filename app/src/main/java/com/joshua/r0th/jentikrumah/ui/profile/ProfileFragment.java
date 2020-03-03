package com.joshua.r0th.jentikrumah.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.joshua.r0th.jentikrumah.R;
import com.joshua.r0th.jentikrumah.melihat_profil;

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
        userId = fAuth.getCurrentUser().getUid();
        contoh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), melihat_profil.class));
            }
        });
        return root;
    }
}