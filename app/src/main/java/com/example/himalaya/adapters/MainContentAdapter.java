package com.example.himalaya.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.himalaya.utils.FragmentCreator;

/*
 *author:The GodFather
 *Date:2020/9/12
 *description:
 */public class MainContentAdapter extends FragmentPagerAdapter {
    public MainContentAdapter(FragmentManager fm){
        super(fm);
     }

    @Override
    public Fragment getItem(int position) {
        return FragmentCreator.getFragment(position);
    }

    @Override
    public int getCount() {
        return FragmentCreator.PAGE_COUNT;
    }
}
