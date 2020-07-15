package com.jwook.gabaekseo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyRecyclerAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<Item> items;

    public MyRecyclerAdapter(Context context, ArrayList<Item> items){
        this.context = context;
        this.items = items;

    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.recycler_item, parent, false);

        VH holder = new VH(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh = (VH)holder;

        Item item = items.get(position);
        vh.recycleiv.setImageResource(item.imgURL);
        vh.recycletv.setText(item.name);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder{

        ImageView recycleiv;
        TextView recycletv;

        public VH(@NonNull View itemView) {
            super(itemView);

            recycleiv = itemView.findViewById(R.id.recycle_iv);
            recycletv = itemView.findViewById(R.id.recycle_tv);


        }
    }
}
