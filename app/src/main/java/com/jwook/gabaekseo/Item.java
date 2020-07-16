package com.jwook.gabaekseo;

import android.net.Uri;

public class Item {
    String name;
    String address;
    String tel;
    String time;
    String url;


    public Item(String name, String address, String tel, String time, String url){
      this.name = name;
      this.address = address;
      this.tel = tel;
      this.time = time;
      this.url = url;

    }

    public Item() {
    }
}
