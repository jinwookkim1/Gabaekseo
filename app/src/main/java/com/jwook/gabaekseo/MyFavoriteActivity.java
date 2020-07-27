package com.jwook.gabaekseo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;

public class MyFavoriteActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MyRecyclerAdapter fvrecyclerAdapter;
    ArrayList<Item> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favorite);

        //Data.xml에 저장된 fav 들 읽어오기
        SharedPreferences shard = getSharedPreferences("Data", MODE_PRIVATE);
        String fv = shard.getString("fav", "");

        // ;를 기준으로 분리
        String[] fvs = fv.split(";");

        for (int i = 0; i < fvs.length - 1; i++) {
            String[] fvs1 = fvs[i].split("&");

            Item item = new Item(fvs1[0], fvs1[1], fvs1[2], fvs1[3], fvs1[4], 0.0, 0.0);
            items.add(item);
        }


        fvrecyclerAdapter = new MyRecyclerAdapter(this, items);

        recyclerView = findViewById(R.id.fv_recycle);
        recyclerView.setAdapter(fvrecyclerAdapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}


