package com.example.oladocapp.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.oladocapp.Adapter;
import com.example.oladocapp.Adapter1;
import com.example.oladocapp.Modalsub;
import com.example.oladocapp.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


public class Gynecologist extends Fragment {
    RecyclerView recyclerView1;
    Adapter1 adapter1;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_gynecologist, container, false);
        recyclerView1 = view.findViewById(R.id.frecy1);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView1.setLayoutManager(layoutManager);

        FirebaseRecyclerOptions<Modalsub> options =
                new FirebaseRecyclerOptions.Builder<Modalsub>()
                        .setQuery(FirebaseDatabase.getInstance().getReference("Doctor").child("Gynecologist"), Modalsub.class)
                        .build();

        adapter1=new Adapter1(options);
        recyclerView1.setAdapter(adapter1);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter1.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter1.stopListening();
    }

}