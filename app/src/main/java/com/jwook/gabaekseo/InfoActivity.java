package com.jwook.gabaekseo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    ImageView info_iv;
    TextView info_name;
    TextView info_add;
    TextView info_tel;
    TextView info_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        info_iv.findViewById(R.id.info_iv);
        info_name.findViewById(R.id.info_name);
        info_add.findViewById(R.id.info_add);
        info_tel.findViewById(R.id.info_tel);
        info_time.findViewById(R.id.info_time);









    }
}
