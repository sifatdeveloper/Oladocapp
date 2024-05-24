package com.example.oladocapp.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.oladocapp.AdapterOrthopedic;
import com.example.oladocapp.Modalsub;
import com.example.oladocapp.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Orthopedic extends Fragment {
    RecyclerView recyclerVieworthopdic;
    AdapterOrthopedic adapterOrthopedic;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_orthopedic, container, false);
        recyclerVieworthopdic = view.findViewById(R.id.recyorthopedic);
       LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
       recyclerVieworthopdic.setLayoutManager(layoutManager);
        FirebaseRecyclerOptions<Modalsub> options =
                new FirebaseRecyclerOptions.Builder<Modalsub>()
                        .setQuery(FirebaseDatabase.getInstance().getReference("Doctor").child("Orthopedic Surgeon"), Modalsub.class)
                        .build();
        adapterOrthopedic=new AdapterOrthopedic(options);
        recyclerVieworthopdic.setAdapter(adapterOrthopedic);



        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        adapterOrthopedic.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapterOrthopedic.stopListening();
    }
}