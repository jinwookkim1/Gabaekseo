package com.jwook.gabaekseo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    ImageView iv;
    TextView tvName;
    TextView tvAdd;
    TextView tvTel;
    TextView tvTime;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Intent intent = getIntent();




    }
}
