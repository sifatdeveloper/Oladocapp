package com.example.oladocapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterAppoinment extends RecyclerView.Adapter<AdapterAppoinment.AppoinmentViewHolder> {
    private List<Modalsub> appoinmentList;

    public AdapterAppoinment(List<Modalsub> appoinmentList) {
        this.appoinmentList = appoinmentList;
    }

    @NonNull
    @Override
    public AppoinmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customprofile, parent, false);
        return new AppoinmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppoinmentViewHolder holder, int position) {
        Modalsub modalsub = appoinmentList.get(position);
        holder.textView1.setText(String.valueOf(modalsub.getName()));
        holder.textView2.setText(String.valueOf(modalsub.getEmail()));
        holder.textView3.setText(String.valueOf(modalsub.getEducation()));
        holder.textView4.setText(String.valueOf(modalsub.getId()));
        holder.textView5.setText(String.valueOf(modalsub.getGender()));
        holder.textView6.setText(String.valueOf(modalsub.getPassword()));
        holder.textView7.setText(String.valueOf(modalsub.getProfession()));
        Glide.with(holder.profile_image.getContext())
                .load(modalsub.getImage())
                .into(holder.profile_image);
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return appoinmentList.size();
    }

    public static class AppoinmentViewHolder extends RecyclerView.ViewHolder {
        CircleImageView profile_image;
        TextView textView1,textView2,textView3,textView4,textView5,textView6,textView7;
        ConstraintLayout mainlayout;
        AppCompatButton button;

        public AppoinmentViewHolder(@NonNull View itemView) {
            super(itemView);
            profile_image=itemView.findViewById(R.id.circleImageView2);
            textView1=itemView.findViewById(R.id.textView5);
            textView2=itemView.findViewById(R.id.textView6);
            textView3=itemView.findViewById(R.id.textView7);
            mainlayout=itemView.findViewById(R.id.layout);
            textView4=itemView.findViewById(R.id.textView8);
            textView5=itemView.findViewById(R.id.textView9);
            textView6=itemView.findViewById(R.id.textView10);
            textView7=itemView.findViewById(R.id.textView11);
            button=itemView.findViewById(R.id.book);
        }
    }
}

