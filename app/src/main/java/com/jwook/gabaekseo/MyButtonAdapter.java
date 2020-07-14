package com.jwook.gabaekseo;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyButtonAdapter extends FragmentPagerAdapter {

    int ButtonTabs;

    public MyButtonAdapter(@NonNull FragmentManager fm, int numTabs) {
        super(fm, numTabs);
        this.ButtonTabs = numTabs;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                Fragment_1 tab1 = new Fragment_1();
                return tab1;
            case 1:
                Fragment_2 tab2 = new Fragment_2();
                return tab2;
            case 2:
                Fragment_3 tab3 = new Fragment_3();
                return tab3;
            case 3:
                Fragment_4 tab4 = new Fragment_4();
                return tab4;
            case 4:
                Fragment_5 tab5 = new Fragment_5();
                return tab5;

            default:
                return null;

        }
        //return null;
    }

    @Override
    public int getCount() {
        return ButtonTabs;
    }
}
