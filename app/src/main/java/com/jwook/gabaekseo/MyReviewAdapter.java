package com.jwook.gabaekseo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyReviewAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<ReItem> reItems;

    public MyReviewAdapter(Context context, ArrayList<ReItem> reItems){
        this.context = context;
        this.reItems = reItems;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.review_item, parent, false);
        VH holder = new VH(itemView);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh = (VH)holder;
        final ReItem reItem = reItems.get(position);
        Glide.with(context).load(reItem.reUrl).into(vh.cv);
        vh.reName.setText(reItem.reName);
        vh.reView.setText(reItem.reView);


    }

    @Override
    public int getItemCount() {
        return reItems.size();
    }


    class VH extends RecyclerView.ViewHolder{

        CircleImageView cv;
        TextView reName;
        TextView reView;


        public VH(@NonNull View itemView) {
            super(itemView);

            cv = (itemView).findViewById(R.id.rv_img);
            reName = (itemView).findViewById(R.id.rv_name);
            reView = (itemView).findViewById(R.id.rv_review);
        }
    }
}
