package com.jwook.gabaekseo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.usermgmt.response.model.Profile;
import com.kakao.usermgmt.response.model.UserAccount;
import com.kakao.util.exception.KakaoException;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    SearchView searchView;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    TextView tvName;
    CircleImageView iv;

    MyButtonAdapter myButtonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_view);
        navigationView = findViewById(R.id.nv_view);

        View headerView = navigationView.getHeaderView(0);
        tvName = headerView.findViewById(R.id.user_name);
        iv = headerView.findViewById(R.id.cv_img);

        tvName.setText(G.nickName);
        Glide.with(this).load(G.imgUrl).into(iv);



        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id){
                    case R.id.favorites:
                        Intent intent = new Intent(MainActivity.this, MyFavoriteActivity.class);
                        startActivity(intent);
                        Toast.makeText(MainActivity.this, "내 즐겨찾기로 이동", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.my_reviews:
                        Toast.makeText(MainActivity.this, "내가 작성한 리뷰로 이동", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.my_info:
                        Toast.makeText(MainActivity.this, "로그아웃되었습니다.", Toast.LENGTH_SHORT).show();
                        UserManagement.getInstance().requestLogout(new LogoutResponseCallback() {
                            @Override
                            public void onCompleteLogout() {
                                startActivity(new Intent(MainActivity.this, LobbyActivity.class) );
                            }
                        });
                        break;
                }

                drawerLayout.closeDrawer(navigationView);

                return false;
            }
        });

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.title_text);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_dehaze_black_24dp);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("공 원"));
        tabs.addTab(tabs.newTab().setText("카 페"));
        tabs.addTab(tabs.newTab().setText("음식점"));
        tabs.addTab(tabs.newTab().setText("숙 소"));
        tabs.addTab(tabs.newTab().setText("동물병원"));
        tabs.setTabGravity(tabs.GRAVITY_FILL);

        final ViewPager viewPager = findViewById(R.id.viewpager);
        myButtonAdapter = new MyButtonAdapter(getSupportFragmentManager(), 5);
        viewPager.setAdapter(myButtonAdapter);
        tabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));


        ActionBar actionBar = getSupportActionBar();
        actionBar.show();

    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        MenuItem item = menu.findItem(R.id.bar_search);
        searchView = (SearchView) item.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("검색어를 입력하세요.");
        searchView.setQuery("",false);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(MainActivity.this, query+"를 검색했습니다.", Toast.LENGTH_SHORT).show();

                G.itemlist.clear();
                Fragment_1 frag1 = (Fragment_1)myButtonAdapter.tabs[0];
                for (Item item1 : frag1.arrayList){
                    if (item1.name.contains(query) || item1.address.contains(query)){
                        G.itemlist.add(item1);
                    }
                }

                G.itemlist.clear();

                //카페리스트 (Fragment2객체의 멤버변수인 arrayList객체의 요소들 중에 query 에 해당하는 글씨를 포함한 요소를 찾기)
                Fragment_2 frag2= (Fragment_2)myButtonAdapter.tabs[1];
                for( Item item2 : frag2.arrayList ){
                    if( item2.name.contains(query) || item2.address.contains(query) ){
                        //찾은 요소를 G.itemList[SearchActivity에서 사용할 ArrayList]
                        G.itemlist.add( item2 );
                    }
                }

                G.itemlist.clear();
                Fragment_3 frag3 = (Fragment_3)myButtonAdapter.tabs[2];
                for (Item item3 : frag3.arrayList){
                    if (item3.name.contains(query) || item3.address.contains(query)){
                        G.itemlist.add(item3);
                    }
                }

                G.itemlist.clear();
                Fragment_4 frag4 = (Fragment_4)myButtonAdapter.tabs[3];
                for (Item item4 : frag4.arrayList){
                    if (item4.name.contains(query) || item4.address.contains(query)){
                        G.itemlist.add(item4);
                    }
                }

                G.itemlist.clear();
                Fragment_5 frag5 = (Fragment_5)myButtonAdapter.tabs[4];
                for (Item item5 : frag5.arrayList){
                    if (item5.name.contains(query) || item5.address.contains(query)){
                        G.itemlist.add(item5);
                    }
                }

                startActivity(new Intent(MainActivity.this, SearchActivity.class));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;

        }

        return super.onOptionsItemSelected(item);
    }
}


