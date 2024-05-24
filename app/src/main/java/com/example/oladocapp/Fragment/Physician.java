package com.example.oladocapp.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.oladocapp.AdapterOrthopedic;
import com.example.oladocapp.AdapterPhysican;
import com.example.oladocapp.Modalsub;
import com.example.oladocapp.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


public class Physician extends Fragment {
    RecyclerView recyclerViewphysician;
    AdapterPhysican adapterPhysican;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_physician, container, false);
        recyclerViewphysician=view.findViewById(R.id.recyphysican);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        recyclerViewphysician.setLayoutManager(linearLayoutManager);
        FirebaseRecyclerOptions<Modalsub> options =
                new FirebaseRecyclerOptions.Builder<Modalsub>()
                        .setQuery(FirebaseDatabase.getInstance().getReference("Doctor").child("Consultant Physician"), Modalsub.class)
                        .build();
        adapterPhysican=new AdapterPhysican(options);
        recyclerViewphysician.setAdapter(adapterPhysican);
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        adapterPhysican.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapterPhysican.stopListening();
    }
}