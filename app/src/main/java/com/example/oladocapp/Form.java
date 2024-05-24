package com.example.oladocapp;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Form extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton radioButton1,radioButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        radioGroup=findViewById(R.id.group);
        radioButton1=findViewById(R.id.radioButton);
        radioButton2=findViewById(R.id.radioButton5);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                
                if (checkedId==R.id.radioButton){
                    radioButton1.setChecked(true);
                    radioButton2.setChecked(false);
                } else if (checkedId==R.id.radioButton5) {
                    radioButton2.setChecked(true);
                    radioButton1.setChecked(false);
                }
            }
        });

    }
}