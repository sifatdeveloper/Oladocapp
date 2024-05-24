package com.example.oladocapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class Adapterprofile extends FirebaseRecyclerAdapter<Modalsub,Adapterprofile.ViewHoloder> {
    public Adapterprofile(@NonNull FirebaseRecyclerOptions<Modalsub> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHoloder holder, int i, @NonNull Modalsub modalsub) {
        Glide.with(holder.imageView.getContext())
                .load(modalsub.getImage())
                .into(holder.imageView);
        holder.textView.setText(String.valueOf(modalsub.getName()));
        holder.textView1.setText(String.valueOf(modalsub.getEmail()));


    }

    @NonNull
    @Override
    public ViewHoloder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view12 = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_profile,parent,false);
        return new ViewHoloder(view12);
    }

    public class ViewHoloder extends RecyclerView.ViewHolder {
        CircleImageView imageView;
        TextView textView,textView1;

        public ViewHoloder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.profile_image);
            textView=itemView.findViewById(R.id.profile_name);
            textView1=itemView.findViewById(R.id.emailprofile);


        }
    }
}
