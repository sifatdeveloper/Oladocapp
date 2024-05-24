package com.example.oladocapp;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.oladocapp.Fragment.HomeFrag;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.oladocapp.Fragment.Navigation;
import com.example.oladocapp.Fragment.Profile;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class Home extends AppCompatActivity  {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    FirebaseAuth firebaseAuth;
    private DatabaseReference userRef;
    private FirebaseDatabase firebaseDatabase;
    BottomNavigationView  bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bottom=findViewById(R.id.navigation);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.main);
        navigationView = findViewById(R.id.navi);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseAuth.getUid().toString();
        firebaseDatabase = FirebaseDatabase.getInstance();
       FirebaseUser user1 = firebaseAuth.getCurrentUser();
        if (user1 != null) {
            String userId = user1.getUid();
            userRef = firebaseDatabase.getReference("User").child(userId);
            View headerView = navigationView.getHeaderView(0);
            TextView name1 = headerView.findViewById(R.id.name);
            TextView email1=headerView.findViewById(R.id.headeremail);
            CircleImageView profile123 = headerView.findViewById(R.id.circleImageView);

            userRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        Modalsub userData = snapshot.getValue(Modalsub.class);
                        name1.setText(userData.getName());
                        email1.setText(userData.getEmail());
                        // Assuming you have a method to load an image into the ImageView
                        Glide.with(Home.this)
                                .load(userData.getImage())
                                .into(profile123);

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // Handle possible errors.
                    Log.e("FirebaseError", "Error: " + error.getMessage());
                }
            });
        }




        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new HomeFrag()).commit();
        bottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.menu_item_1) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, new HomeFrag()).commit();
                    return true;
                }else if (menuItem.getItemId() == R.id.menu_item_2) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, new Profile()).commit();
                    return true;
                } else if (menuItem.getItemId()==R.id.menu_item_3) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, new Navigation()).commit();
                    return true;


                }
                return true;
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()==R.id.account){
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new Profile()).commit();
                } else if (item.getItemId()==R.id.setting) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, new HomeFrag()).commit();
                } else if (item.getItemId()==R.id.logout) {
                    Intent intent = new Intent(Home.this, Login.class);
                    startActivity(intent);
                    finish();

                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

    }



}


