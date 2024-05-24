package com.example.oladocapp;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class DoctorLogin extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    TextView textView1, textView2, textView3, textView4;
    CircleImageView circleImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_login);

        textView1 = findViewById(R.id.profiled_name);
        textView2 = findViewById(R.id.email);
        textView3 = findViewById(R.id.profession);
        textView4 = findViewById(R.id.education);
        circleImageView = findViewById(R.id.doctor_image);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        if (user != null) {
            String userId = user.getUid();
            checkUserRole(userId);
        } else {
            Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show();
        }
    }

    private void checkUserRole(String userId) {
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Doctor");

        // Check for Child Specialist role
        userRef.child("Child Specialist").child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    updateUI(snapshot);
                } else {
                    // If not found, check for Skin Specialist role
                    checkSkinSpecialistRole(userId);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(DoctorLogin.this, "Database error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void checkSkinSpecialistRole(String userId) {
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Doctor");
        userRef.child("Skin Specialist").child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    updateUI(snapshot);
                } else {
                    Toast.makeText(DoctorLogin.this, "User role not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(DoctorLogin.this, "Database error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void checkGynecologistRole(String userId) {
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Doctor");
        userRef.child("Gynecologist").child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    updateUI(snapshot);
                } else {
                    Toast.makeText(DoctorLogin.this, "User role not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(DoctorLogin.this, "Database error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void checkOrthopedicSurgeonRole(String userId) {
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Doctor");
        userRef.child("Orthopedic Surgeon").child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    updateUI(snapshot);
                } else {
                    Toast.makeText(DoctorLogin.this, "User role not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(DoctorLogin.this, "Database error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void checkConsultantPhysicianRole(String userId) {
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Doctor");
        userRef.child("Consultant Physician").child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    updateUI(snapshot);
                } else {
                    Toast.makeText(DoctorLogin.this, "User role not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(DoctorLogin.this, "Database error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void updateUI(DataSnapshot snapshot) {
        Modalsub modalsub = snapshot.getValue(Modalsub.class);
        if (modalsub != null) {
            textView1.setText(modalsub.getName());
            textView2.setText(modalsub.getEmail());
            textView3.setText(modalsub.getProfession());
            textView4.setText(modalsub.getEducation());
            Glide.with(DoctorLogin.this)
                    .load(modalsub.getImage())
                    .into(circleImageView);
        } else {
            Toast.makeText(this, "Error fetching user data", Toast.LENGTH_SHORT).show();
        }
    }
}
