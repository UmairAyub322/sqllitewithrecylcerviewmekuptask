package com.example.sqllitewithrecylcerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adpter extends RecyclerView.Adapter<adpter.DbViewHolder> {
    private ArrayList<modelclass> modelclassArrayList;

    public adpter(ArrayList<modelclass> modelclassArrayList) {
        this.modelclassArrayList = modelclassArrayList;
    }

    @NonNull
    @Override
    public DbViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View singleRow= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row,parent,false);
        DbViewHolder dbViewHolder= new DbViewHolder(singleRow);
        return dbViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DbViewHolder holder, int position) {
holder.name.setText(modelclassArrayList.get(position).getName());
holder.rollno.setText(modelclassArrayList.get(position).getRollno());
    }

    @Override
    public int getItemCount() {
        return modelclassArrayList.size();
    }

    class DbViewHolder extends RecyclerView.ViewHolder{
             TextView name,rollno;
        public DbViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.nameTV);
            rollno=itemView.findViewById(R.id.rollnoTV);
        }
    }
}
