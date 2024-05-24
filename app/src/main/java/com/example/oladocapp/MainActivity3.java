package com.example.oladocapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {
    RecyclerView recyclerViewappoinment;
    AdapterAppoinment adapterAppoinment;
    List<Modalsub> combinedList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        recyclerViewappoinment = findViewById(R.id.appoinmentrecy);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewappoinment.setLayoutManager(layoutManager);

        // Initialize the adapter with an empty list first
        adapterAppoinment = new AdapterAppoinment(combinedList);
        recyclerViewappoinment.setAdapter(adapterAppoinment);

        // Fetch data from multiple child nodes
        fetchData();
    }

    private void fetchData() {
        DatabaseReference doctorRef = FirebaseDatabase.getInstance().getReference("Doctor");

        // Fetch data from "Skin Specialist"
        doctorRef.child("Skin Specialist").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Modalsub modalsub = snapshot.getValue(Modalsub.class);
                    combinedList.add(modalsub);
                }
                // Fetch data from "Gynecologist" after fetching "Skin Specialist"
                fetchGynecologistData();
                fetchChildData();
                fetchorthopedicData();
                fetchPhysicianData();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });
    }

    private void fetchGynecologistData() {
        DatabaseReference doctorRef = FirebaseDatabase.getInstance().getReference("Doctor");

        doctorRef.child("Gynecologist").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Modalsub modalsub = snapshot.getValue(Modalsub.class);
                    combinedList.add(modalsub);
                }
                // Notify adapter that data has changed
                adapterAppoinment.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });
    }
    private void fetchChildData() {
        DatabaseReference doctorRef = FirebaseDatabase.getInstance().getReference("Doctor");

        doctorRef.child("Child Specialist").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Modalsub modalsub = snapshot.getValue(Modalsub.class);
                    combinedList.add(modalsub);
                }
                // Notify adapter that data has changed
                adapterAppoinment.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });
    }
    private void fetchorthopedicData() {
        DatabaseReference doctorRef = FirebaseDatabase.getInstance().getReference("Doctor");

        doctorRef.child("Orthopedic Surgeon").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Modalsub modalsub = snapshot.getValue(Modalsub.class);
                    combinedList.add(modalsub);
                }
                // Notify adapter that data has changed
                adapterAppoinment.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });
    }
    private void fetchPhysicianData() {
        DatabaseReference doctorRef = FirebaseDatabase.getInstance().getReference("Doctor");

        doctorRef.child("Consultant Physician").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Modalsub modalsub = snapshot.getValue(Modalsub.class);
                    combinedList.add(modalsub);
                }
                // Notify adapter that data has changed
                adapterAppoinment.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();
        // Start listening if you use FirebaseRecyclerAdapter, but in this case, we use a custom adapter
        // adapterAppoinment.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        // Stop listening if you use FirebaseRecyclerAdapter, but in this case, we use a custom adapter
        // adapterAppoinment.stopListening();
    }
}
