package com.example.zimzik.actionwithviewpager;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.ViewGroup;

import java.util.List;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private static final String TAG = ViewPagerAdapter.class.getSimpleName();
    
    private List<Fragment> mFragments;
    private List<CharSequence> mTitles;
    private FragmentManager mFragmentManager;

    public ViewPagerAdapter(FragmentManager fm, List<Fragment> fragments, List<CharSequence> titles) {
        super(fm);
        mFragmentManager = fm;
        mFragments = fragments;
        mTitles = titles;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }

    public void addFragment(Fragment fragment, int index, CharSequence title) {
        mFragments.add(index, fragment);
        mTitles.add(index, title);
        Log.d(TAG, "addFragment: new element add, size of element list is: " + mFragments.size());
        notifyDataSetChanged();
    }

    public void removeFragment(int index) {
        mFragments.remove(index);
        mTitles.remove(index);
        Log.d(TAG, "addFragment: new element removed, size of element list is: " + mFragments.size());
        notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
        Log.d(TAG, "getItem: item #" + position);
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        Log.d(TAG, "getCount: fragments size: " + mFragments.size());
        return mFragments.size();
    }
}
