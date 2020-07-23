package com.jwook.gabaekseo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class InfoActivity extends AppCompatActivity {

    ImageView iv;
    TextView tvName;
    TextView tvAdd;
    TextView tvTel;
    TextView tvTime;
    GoogleMap Gmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);


        iv = findViewById(R.id.info_iv);
        tvName = findViewById(R.id.info_name);
        tvAdd = findViewById(R.id.info_add);
        tvTel = findViewById(R.id.info_tel);
        tvTime = findViewById(R.id.info_time);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String address = intent.getStringExtra("address");
        String tel = intent.getStringExtra("tel");
        String time = intent.getStringExtra("time");
        String url = intent.getStringExtra("url");
        final double x = intent.getDoubleExtra("x", 37.556355);
        final double y = intent.getDoubleExtra("y", 126.977929);


        Glide.with(this).load(url).into(iv);
        tvName.setText(name);
        tvAdd.setText(address);
        tvTel.setText(tel);
        tvTime.setText(time);

        FragmentManager fragmentManager = getSupportFragmentManager();
        final SupportMapFragment mapFragment = (SupportMapFragment) fragmentManager.findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                Gmap = googleMap;

                LatLng place = new LatLng(x, y);
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(place);
                markerOptions.title("Here");
                Gmap.addMarker(markerOptions);

                Gmap.animateCamera(CameraUpdateFactory.newLatLngZoom(place, 15));
                UiSettings settings = Gmap.getUiSettings();
                settings.setZoomControlsEnabled(true);


            }
        });

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
