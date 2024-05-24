package com.example.oladocapp.Fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.oladocapp.Home;
import com.example.oladocapp.HospitalAdapter;
import com.example.oladocapp.MainActivity2;
import com.example.oladocapp.MainActivity3;
import com.example.oladocapp.Modal;
import com.example.oladocapp.Modal1;
import com.example.oladocapp.ModelHospital;
import com.example.oladocapp.R;
import com.example.oladocapp.VideoAdapter;
import com.example.oladocapp.categoryAdapter;

import java.util.ArrayList;

public class HomeFrag extends Fragment {
    private TextView textView;
    AppCompatButton button;
    private int[] colors = {Color.RED, Color.GREEN, Color.BLUE};
    private int currentColorIndex = 0;
    ImageView cancell;
    RecyclerView recyclerView,recyclerView1,recyclerView2;
    ArrayList<Modal> arrayList=new ArrayList<>();
    ArrayList<Modal1>arrayList1=new ArrayList<>();
    ArrayList<ModelHospital>arrayList2=new ArrayList<>();
    CardView appoinment;


    private Handler colorChangeHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            textView.setTextColor(colors[currentColorIndex]);
            currentColorIndex = (currentColorIndex + 1) % colors.length;
            colorChangeHandler.sendEmptyMessageDelayed(0, 1000); // Change color every second
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView1=view.findViewById(R.id.recy1);
        recyclerView=view.findViewById(R.id.recy);
        appoinment=view.findViewById(R.id.card1);
        recyclerView2=view.findViewById(R.id.recy2);

        //Hospital Adapter
        LinearLayoutManager manager=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView2.setLayoutManager(manager);
        arrayList2.add(new ModelHospital(R.drawable.hospital1,"Fisal Hospital"));
        arrayList2.add(new ModelHospital(R.drawable.hospital2,"Shifa Hospital"));
        arrayList2.add(new ModelHospital(R.drawable.hospital3,"Lahore-General-Hospital"));
        arrayList2.add(new ModelHospital(R.drawable.hospital1,"Fisal Hospital"));
        HospitalAdapter adapter2=new HospitalAdapter(arrayList2,getContext());
        recyclerView2.setAdapter(adapter2);






        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager12=new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        recyclerView.setLayoutManager(manager12);
        arrayList.add(new Modal("https://www.youtube.com/watch?v=77_fA5NZwpw"));
        arrayList.add(new Modal("https://www.youtube.com/watch?v=Cz8MSGSc7-E"));
        arrayList.add(new Modal("https://www.youtube.com/watch?v=qn9-85G5lCQ"));
        arrayList.add(new Modal("https://www.youtube.com/watch?v=wQ44SIb6P_8"));
        VideoAdapter adapter=new VideoAdapter(getContext(),arrayList);
        recyclerView.setAdapter(adapter);

        //Recyclevie1
        LinearLayoutManager manager1=new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        recyclerView1.setLayoutManager(manager1);

        arrayList1.add(new Modal1(R.drawable.skin,"Skin Specialist"));
        arrayList1.add(new Modal1(R.drawable.gynec,"Gynecologist"));
        arrayList1.add(new Modal1(R.drawable.child,"Child Specialist"));
        arrayList1.add(new Modal1(R.drawable.orthopedic,"Orthopedic Surgeon"));
        arrayList1.add(new Modal1(R.drawable.physician,"Consultant Physician"));


        categoryAdapter adapter1=new categoryAdapter(getContext(),arrayList1);
        recyclerView1.setAdapter(adapter1);

        appoinment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), MainActivity3.class);
                startActivity(intent);

            }
        });
        return view;
    }
}