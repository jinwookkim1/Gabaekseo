package com.jwook.gabaekseo;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_1 extends Fragment {


    ArrayList<Item> arrayList = new ArrayList<>();
    MyRecyclerAdapter recyclerAdapter;
    RecyclerView recyclerView;

    public Fragment_1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_1, container, false);
        recyclerView = view.findViewById(R.id.recycle);
        recyclerAdapter = new MyRecyclerAdapter(getActivity(), arrayList);
        recyclerView.setAdapter(recyclerAdapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        parklist();

    }

    public void parklist(){
        final String serverUrl = "http://refoz.dothome.co.kr/gabaekseo/parklist.json";

        new Thread(){
            @Override
            public void run() {

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        arrayList.clear();
                        recyclerAdapter.notifyDataSetChanged();
                    }
                });

                try {
                    URL url = new URL(serverUrl);
                    HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    connection.setUseCaches(false);

                    InputStream is = connection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);
                    Gson gson = new Gson();
                    Item[] items = gson.fromJson(isr, Item[].class);

                    for(Item item: items){
                        arrayList.add(item);
                    }

                    //리사이클러뷰를 갱신
                    getActivity().runOnUiThread(new Runnable() {
                       @Override
                        public void run() {
                            recyclerAdapter.notifyDataSetChanged();
                        }
                   });




                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }
}
