package com.jwook.gabaekseo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ReviewActivity extends AppCompatActivity {
    EditText et;
    Button rvbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        et = findViewById(R.id.re_tv);
        rvbtn = findViewById(R.id.re_btn);


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

    public void clickRev(View view) {

        //저장할 데이터
        String msg = et.getText().toString();
        String profileUrl= G.imgUrl;
        String profileName= G.nickName;

        SharedPreferences shared1 = getSharedPreferences("Data", MODE_PRIVATE);

        String data = shared1.getString("revs", "");
        data += profileName+"&"+msg+"&"+profileUrl+";";


        SharedPreferences.Editor editor = shared1.edit();
        editor.putString("revs", data);
        editor.commit();

        Intent intent= new Intent(this, InfoActivity.class);
        startActivity(intent);

        Toast.makeText(this, "리뷰작성 완료", Toast.LENGTH_SHORT).show();

    }
}
