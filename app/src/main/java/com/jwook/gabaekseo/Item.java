package com.jwook.gabaekseo;

import android.net.Uri;

public class Item {
    String name;
    String address;
    String tel;
    String time;
    String url;
    Double x;
    Double y;


    public Item(String name, String address, String tel, String time, String url, Double x, Double y){
      this.name = name;
      this.address = address;
      this.tel = tel;
      this.time = time;
      this.url = url;
      this.x = x;
      this.y = y;

    }

    public Item() {
    }
}
