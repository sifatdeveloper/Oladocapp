package com.example.oladocapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.autofill.AutofillValue;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ViewFlipper;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    ViewFlipper viewFlipper;
    RadioGroup radioGroup;
    RadioButton radioButton1,radioButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewFlipper=findViewById(R.id.viewFlipper);
        radioGroup=findViewById(R.id.radio);
        radioButton1=findViewById(R.id.radioButton1);
        radioButton2=findViewById(R.id.radioButton2);

        final int[] viewOrder = {0, 1, 2,3};
        final int[] radioButtonIds = {R.id.radioButton1, R.id.radioButton2, R.id.radioButton3,R.id.radioButton4};
        final Handler handler = new Handler();
        final int flipInterval = 2000;
        final int[] viewIndex = {0};

        Runnable flipRunnable = new Runnable() {
            @Override
            public void run() {
                if (viewIndex[0] < viewOrder.length) {
                    viewFlipper.setDisplayedChild(viewOrder[viewIndex[0]]);
                    radioGroup.check(radioButtonIds[viewIndex[0]]);
                    viewIndex[0]++;
                    handler.postDelayed(this, flipInterval);
                } else {
                    viewFlipper.stopFlipping();
                    Intent intent = new Intent(MainActivity.this, Login.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        handler.postDelayed(flipRunnable, flipInterval);
    }


}

