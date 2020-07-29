package com.jwook.gabaekseo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;

public class MyReviewActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MyReviewAdapter reviewAdapter;
    ArrayList<ReItem> reItems = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_review);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        reviewAdapter = new MyReviewAdapter(this, reItems);
        recyclerView = findViewById(R.id.rv_recycle);
        recyclerView.setAdapter(reviewAdapter);


    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences shared1 = getSharedPreferences("Data", MODE_PRIVATE);
        String rev = shared1.getString("revs", "");
        String[] revs = rev.split(";");

        for(int i = 0; i < revs.length; i++){
            String[] revs1 = revs[i].split("&");
            if( revs1.length != 3) continue;

            ReItem reItem = new ReItem(revs1[0], revs1[1], revs1[2]);
            reItems.add(reItem);
            reviewAdapter.notifyItemInserted(reItems.size()-1);
        }
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
