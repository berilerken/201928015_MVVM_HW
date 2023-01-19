package com.example.a201928015_mvvm_hw.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a201928015_mvvm_hw.Database.Model;
import com.example.a201928015_mvvm_hw.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.CatViewHolder>{

    private Context cntx;
    private List<Model> cats;

    public Adapter(Context context, List<Model> cats) {
        this.cntx = context;
        this.cats = cats;
    }

    @NonNull
    @Override
    public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CatViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.itempic,parent,false));
    }

    @Override
    public int getItemCount() {
        return cats.size();
    }

    public void getAllDatas(List<Model> cats)
    {
        this.cats=cats;
    }

    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, int position) {
        Model cat=cats.get(position);
        Picasso.get().load(cat.getUrl()).into(holder.image);
    }


    public static class CatViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView image;

        public CatViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.imageView);
        }
    }
}