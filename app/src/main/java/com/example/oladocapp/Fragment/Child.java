package com.example.oladocapp.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.oladocapp.Adapter;
import com.example.oladocapp.AdapterChild;
import com.example.oladocapp.Modalsub;
import com.example.oladocapp.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Child extends Fragment {
    RecyclerView recyclerViewchild;
    AdapterChild adapterChild;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_child, container, false);
        recyclerViewchild=view.findViewById(R.id.recychild);
        LinearLayoutManager manager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerViewchild.setLayoutManager(manager);
        FirebaseRecyclerOptions<Modalsub> options =
                new FirebaseRecyclerOptions.Builder<Modalsub>()
                        .setQuery(FirebaseDatabase.getInstance().getReference("Doctor").child("Child Specialist"), Modalsub.class)
                        .build();

        adapterChild =new AdapterChild(options);
        recyclerViewchild.setAdapter(adapterChild);

        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        adapterChild.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
       adapterChild .stopListening();
    }
}