package com.jwook.gabaekseo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

import java.util.ArrayList;

public class InfoActivity extends AppCompatActivity {

    ImageView iv;
    TextView tvName;
    TextView tvAdd;
    TextView tvTel;
    TextView tvTime;
    GoogleMap Gmap;
    String url;

    RecyclerView recyclerView;
    MyReviewAdapter reviewAdapter;
    ArrayList<ReItem> reItems = new ArrayList<>();


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
        url = intent.getStringExtra("url");
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


        reviewAdapter = new MyReviewAdapter(this, reItems);
        recyclerView = findViewById(R.id.info_recycle);
        recyclerView.setAdapter(reviewAdapter);





    }

    @Override
    protected void onResume() {

        super.onResume();

        reItems.clear();
        reviewAdapter.notifyDataSetChanged();

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.review, menu);
        MenuItem item = menu.findItem(R.id.bar_review);
        item.getActionView();
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작
                finish();
                return true;
            }
            case R.id.bar_review:{
                Intent intent = new Intent(this, ReviewActivity.class);
                startActivity(intent);
            }

        }
        return super.onOptionsItemSelected(item);
    }

    public void clickBtn(View view) {

       String name = tvName.getText().toString();
       String address = tvAdd.getText().toString();
       String tel = tvTel.getText().toString();
       String time = tvTime.getText().toString();


        SharedPreferences shared = getSharedPreferences("Data", MODE_PRIVATE);
        String favs = shared.getString("fav", "");

        favs += name+"&"+address+"&"+tel+"&"+time+"&"+url+";";

        SharedPreferences.Editor editor = shared.edit();
        editor.putString("fav", favs);
        editor.commit();

        Toast.makeText(this, "즐겨찾기 추가완료", Toast.LENGTH_SHORT).show();

    }





}


