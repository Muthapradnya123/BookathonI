package com.android.bookathon;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import static com.android.bookathon.TabFragment.int_item;

/**
 * Created by LENOVO on 23-03-2017.
 */

public class MyAdapter extends FragmentPagerAdapter{

    public MyAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position)
        {
            case 0:
                return new home();
            case 1:
                return new Upload();
            case 2:
                return new transaction();
        }

        return null;
    }

    @Override
    public int getCount() {
        return int_item;
    }

    public CharSequence getPageTitle(int position)
    {
        switch(position)
        {
            case 0:
                return "Home";
            case 1:
                return "Upload";
            case 2:
                return "Activity";
        }
        return null;
    }
}
