package com.example.oladocapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

public class Signin extends AppCompatActivity {
    EditText number,otp1;
    CountryCodePicker picker;
    AppCompatButton button;
    Button btnotp;
    String verificationcode;
    PhoneAuthProvider.ForceResendingToken forceResendingToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        number=findViewById(R.id.number);
        picker=findViewById(R.id.codepicker);
        button=findViewById(R.id.enter);
        otp1=findViewById(R.id.editText);
        btnotp=findViewById(R.id.otp);
        otp1.setVisibility(View.GONE);
        btnotp.setVisibility(View.GONE);
        picker.registerCarrierNumberEditText(number);
        String a = picker.getFullNumberWithPlus();


        callbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                // Instant verification or Auto-retrieval of OTP completed
                signInWithPhoneAuthCredential(credential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(Signin.this, "error"+e, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken token) {
                verificationcode=verificationId;
                forceResendingToken=token;
                otp1.setVisibility(View.VISIBLE);
                btnotp.setVisibility(View.VISIBLE);
                Toast.makeText(Signin.this, "otp send successful", Toast.LENGTH_SHORT).show();
            }
        };

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (picker.isValidFullNumber()) {
                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            a,        // Phone number to verify
                            60,                 // Timeout duration
                            TimeUnit.SECONDS,   // Unit of timeout
                            Signin.this,        // Activity (for callback binding)
                            callbacks);         // OnVerificationStateChangedCallbacks
                }else {
                    number.setError("invalid number");
                }
            }
        });
        btnotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String b=otp1.getText().toString();
               PhoneAuthCredential credential= PhoneAuthProvider.getCredential(verificationcode,b);
               signInWithPhoneAuthCredential(credential);
            }
        });
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Intent intent=new Intent(Signin.this, Form.class);
                        Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        FirebaseUser user = task.getResult().getUser();
                    } else {
                        Toast.makeText(this, "failled", Toast.LENGTH_SHORT).show();
                    }
                });
        FirebaseAuth.getInstance().signOut();
    }
}
