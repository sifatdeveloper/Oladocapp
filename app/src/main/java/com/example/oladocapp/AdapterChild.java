package com.example.oladocapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterChild extends FirebaseRecyclerAdapter<Modalsub,AdapterChild.ViewHolder> {
    public AdapterChild(@NonNull FirebaseRecyclerOptions<Modalsub> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int i, @NonNull Modalsub modalsub) {
        if ("Child Specialist".equals(modalsub.getProfession())){
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


        }


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewchild= LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_layout,parent,false);
        return new ViewHolder(viewchild);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CircleImageView profile_image;
        TextView textView1,textView2,textView3,textView4,textView5,textView6,textView7;
        ConstraintLayout mainlayout;
        public ViewHolder(@NonNull View itemView) {
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
        }
    }
}
