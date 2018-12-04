package com.dailymap.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WuchangI on 2018/10/31.
 */

public class MyViewPagerAdapter extends FragmentPagerAdapter
{
    private List<Fragment> mFragmentList;

    public MyViewPagerAdapter(FragmentManager fm, List<Fragment> fragmentList)
    {
        super(fm);
        this.mFragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position)
    {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount()
    {
        return mFragmentList.size();
    }
}
