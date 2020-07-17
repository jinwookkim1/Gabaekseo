package com.jwook.gabaekseo;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyButtonAdapter extends FragmentPagerAdapter {

    int ButtonTabs;
    Fragment[] tabs= new Fragment[5];

    public MyButtonAdapter(@NonNull FragmentManager fm, int numTabs) {
        super(fm, numTabs);
        this.ButtonTabs = numTabs;

        tabs[0]= new Fragment_1();
        tabs[1]= new Fragment_2();
        tabs[2]= new Fragment_3();
        tabs[3]= new Fragment_4();
        tabs[4]= new Fragment_5();
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return tabs[position];
    }

    @Override
    public int getCount() {
        return ButtonTabs;
    }
}
