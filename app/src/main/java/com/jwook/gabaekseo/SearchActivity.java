package com.jwook.gabaekseo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;


public class SearchActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MyRecyclerAdapter screcyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        screcyclerAdapter = new MyRecyclerAdapter(this, G.itemlist);
        recyclerView = findViewById(R.id.sc_recycle);
        recyclerView.setAdapter(screcyclerAdapter);


        //서치결과와 일치하는 item들을 가지고 있는 arrayList 사용
        Toast.makeText(this, ""+G.itemlist.size(), Toast.LENGTH_SHORT).show();



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
