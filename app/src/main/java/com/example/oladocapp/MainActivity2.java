package com.example.oladocapp;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.UUID;

public class MainActivity2 extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri imageUri;
    private String imgurl;
    private ImageView imageView;
    private Button uploadButton;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private Spinner spinner1, spinner;
    private TextInputLayout educ, age2;
    private TextView prof, login1;
    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayList<String> arrayList1 = new ArrayList<>();
    private TextInputEditText email, password, educt, name1, age1;
    private RadioButton doctor, user;
    private RadioGroup radioGroup;
    private FirebaseAuth auth;
    private AppCompatButton save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        initializeViews();
        setupSpinners();
        setupRadioGroup();

        imageView.setOnClickListener(v -> openFileChooser());
        uploadButton.setOnClickListener(v -> {
            uploadImage();
            Toast.makeText(MainActivity2.this, "Uploading image", Toast.LENGTH_SHORT).show();
        });

        auth = FirebaseAuth.getInstance();
        save.setOnClickListener(v -> saveUser());

        login1.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity2.this, Login.class);
            startActivity(intent);
            finish();
        });
    }

    private void initializeViews() {
        imageView = findViewById(R.id.imageView);
        uploadButton = findViewById(R.id.uploadButton);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference("uploads");
        spinner1 = findViewById(R.id.spinner);
        age1 = findViewById(R.id.age);
        login1 = findViewById(R.id.login);
        age2 = findViewById(R.id.textInputLayout6);
        radioGroup = findViewById(R.id.group);
        spinner = findViewById(R.id.spinner2);
        email = findViewById(R.id.editText);
        name1 = findViewById(R.id.editText1);
        password = findViewById(R.id.pass);
        educ = findViewById(R.id.textInputLayout4);
        save = findViewById(R.id.button);
        educt = findViewById(R.id.education);
        doctor = findViewById(R.id.radioButton6);
        user = findViewById(R.id.radioButton7);
        prof = findViewById(R.id.textView4);

        prof.setVisibility(View.GONE);
        educ.setVisibility(View.GONE);
        educt.setVisibility(View.GONE);
        spinner1.setVisibility(View.GONE);
        age2.setVisibility(View.GONE);
        age1.setVisibility(View.GONE);
    }

    private void setupSpinners() {
        arrayList.add("Select Profession");
        arrayList.add("Skin Specialist");
        arrayList.add("Gynecologist");
        arrayList.add("Child Specialist");
        arrayList.add("Orthopedic Surgeon");
        arrayList.add("Consultant Physician");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, arrayList);
        spinner1.setAdapter(arrayAdapter);

        arrayList1.add("Select Gender");
        arrayList1.add("Male");
        arrayList1.add("Female");
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, arrayList1);
        spinner.setAdapter(arrayAdapter2);
    }

    private void setupRadioGroup() {
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radioButton6) {
                doctor.setChecked(true);
                user.setChecked(false);
                age1.setVisibility(View.GONE);
                age2.setVisibility(View.GONE);
                prof.setVisibility(View.VISIBLE);
                spinner1.setVisibility(View.VISIBLE);
                educ.setVisibility(View.VISIBLE);
                educt.setVisibility(View.VISIBLE);
            } else if (checkedId == R.id.radioButton7) {
                user.setChecked(true);
                doctor.setChecked(false);
                age1.setVisibility(View.VISIBLE);
                age2.setVisibility(View.VISIBLE);
                prof.setVisibility(View.GONE);
                educ.setVisibility(View.GONE);
                educt.setVisibility(View.GONE);
                spinner1.setVisibility(View.GONE);
            }
        });
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }
    }

    private void uploadImage() {
        if (imageUri != null) {
            final StorageReference ref = storageReference.child("images/" + UUID.randomUUID().toString());
            ref.putFile(imageUri)
                    .addOnSuccessListener(taskSnapshot -> ref.getDownloadUrl().addOnSuccessListener(uri -> imgurl = uri.toString()))
                    .addOnFailureListener(e -> Toast.makeText(MainActivity2.this, "Upload failed: " + e.getMessage(), Toast.LENGTH_SHORT).show());
        } else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveUser() {
        if (!validateInputs()) {
            return;
        }

        String emailText = email.getText().toString().trim();
        String passwordText = password.getText().toString().trim();
        auth.createUserWithEmailAndPassword(emailText, passwordText)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(MainActivity2.this, "User Created", Toast.LENGTH_SHORT).show();
                        saveUserData();
                    } else {
                        Toast.makeText(MainActivity2.this, "User Not Created", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(Throwable::printStackTrace);
    }

    private boolean validateInputs() {
        if (email.getText().toString().isEmpty()) {
            email.setError("Enter Email");
            return false;
        }
        if (password.getText().toString().isEmpty()) {
            password.setError("Enter Password");
            return false;
        }
        if (name1.getText().toString().isEmpty()) {
            name1.setError("Enter Name");
            return false;
        }
        return true;
    }

    private void saveUserData() {
        String uid = auth.getUid();
        String name = name1.getText().toString();
        String emailText = email.getText().toString();
        String passwordText = password.getText().toString();
        String gender = spinner.getSelectedItem().toString();

        DatabaseReference myRef;
        Modalsub modalsub;

        if (doctor.isChecked()) {
            String profession = spinner1.getSelectedItem().toString();
            String education = educt.getText().toString();
            myRef = FirebaseDatabase.getInstance().getReference("Doctor");
            modalsub = new Modalsub(imgurl, null, name, emailText, passwordText, gender, profession, education, 0);
            myRef.child(profession).child(uid).setValue(modalsub);
            Toast.makeText(MainActivity2.this, "Doctor Created", Toast.LENGTH_SHORT).show();
        } else if (user.isChecked()) {
            String age = age1.getText().toString();
            myRef = FirebaseDatabase.getInstance().getReference("User");
            modalsub = new Modalsub(imgurl, age, name, emailText, passwordText, gender, null, null, 0);
            myRef.child(uid).setValue(modalsub);
            Toast.makeText(MainActivity2.this, "User Created", Toast.LENGTH_SHORT).show();
        }
    }
}
