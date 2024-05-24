package com.example.oladocapp.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.oladocapp.Adapterprofile;
import com.example.oladocapp.Modalsub;
import com.example.oladocapp.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class Profile extends Fragment {
    RecyclerView recyclerView_profile;
    Adapterprofile adapterprofile;
    FirebaseAuth auth;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_profile, container, false);
        auth= FirebaseAuth.getInstance();
        String uid=auth.getCurrentUser().getUid();
        recyclerView_profile=view.findViewById(R.id.profile_list);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        recyclerView_profile.setLayoutManager(linearLayoutManager);
        FirebaseRecyclerOptions<Modalsub> options =
                new FirebaseRecyclerOptions.Builder<Modalsub>()
                        .setQuery(FirebaseDatabase.getInstance().getReference("User"), Modalsub.class)
                        .build();
        adapterprofile=new Adapterprofile(options);
        recyclerView_profile.setAdapter(adapterprofile);
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
       adapterprofile .startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapterprofile.stopListening();
    }
}