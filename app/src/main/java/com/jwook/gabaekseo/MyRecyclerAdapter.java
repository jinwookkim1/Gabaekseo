package com.jwook.gabaekseo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        VH vh = (VH)holder;

        final Item item = items.get(position);
        Glide.with(context).load(item.url).into(vh.recycleiv);
        vh.recyclename.setText(item.name);
        vh.recycleaddress.setText(item.address);
        vh.recycletel.setText(item.tel);
        vh.recycletime.setText(item.time);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, "1111", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(), InfoActivity.class);
                (context).startActivity(intent);

                intent.putExtra("name", item.name);
                intent.putExtra("address", item.address);
                intent.putExtra("tel", item.tel);
                intent.putExtra("time", item.time);
                intent.putExtra("url", item.url);



            }
        });


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder{

        TextView recyclename;
        TextView recycleaddress;
        TextView recycletel;
        TextView recycletime;
        ImageView recycleiv;

        public VH(@NonNull final View itemView) {
            super(itemView);

            recycleiv = itemView.findViewById(R.id.recycle_iv);
            recyclename = itemView.findViewById(R.id.recycle_name);
            recycleaddress = itemView.findViewById(R.id.recycle_add);
            recycletel = itemView.findViewById(R.id.recycle_tel);
            recycletime = itemView.findViewById(R.id.recycle_time);


        }
    }
}
