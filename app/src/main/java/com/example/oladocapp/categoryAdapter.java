package com.example.oladocapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oladocapp.Fragment.Child;
import com.example.oladocapp.Fragment.Gynecologist;
import com.example.oladocapp.Fragment.Orthopedic;
import com.example.oladocapp.Fragment.Physician;
import com.example.oladocapp.Fragment.Skin;

import java.util.ArrayList;

public class categoryAdapter extends RecyclerView.Adapter<categoryAdapter.ViewHolder> {

    Context context;
    ArrayList<Modal1> arrayList=new ArrayList<>();

    public categoryAdapter(Context context, ArrayList<Modal1> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cate_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Modal1 modal1 = arrayList.get(position);
        holder.imageView.setImageResource(modal1.image);
        holder.name.setText(modal1.name);
        holder.linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (holder.getAdapterPosition()) {
                    case 0:
                        Skin skin = new Skin();
                        loadFragment(skin);
                        break;
                    case 1:
                        Gynecologist Gynecologist = new Gynecologist();
                        loadFragment(Gynecologist);
                        break;
                    case 2:
                        Child child = new Child();
                        loadFragment(child);
                        break;
                    case 3:
                        Orthopedic orthopedic = new Orthopedic();
                        loadFragment(orthopedic);
                        break;
                    case 4:
                        Physician physician = new Physician();
                        loadFragment(physician);
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name;
        LinearLayout linear;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            linear = itemView.findViewById(R.id.linearLayout);
        }
    }

    private void loadFragment(Fragment fragment) {
        if (context instanceof FragmentActivity) {
            FragmentManager manager = ((FragmentActivity) context).getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.frame, fragment);
            transaction.commit();
        } else {
            throw new IllegalStateException("Context is not an instance of FragmentActivity");
        }
    }


}
