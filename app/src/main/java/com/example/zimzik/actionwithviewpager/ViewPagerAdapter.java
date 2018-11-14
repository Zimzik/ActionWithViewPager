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

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //assert(0 <= position && position < mFragments.size());
        FragmentTransaction trans = mFragmentManager.beginTransaction();
        trans.remove(mFragments.get(position));
        trans.commit();
        mFragments.set(position, null);
    }

    @NonNull
    @Override
    public Fragment instantiateItem(ViewGroup container, int position){
        Fragment fragment = getItem(position);
        FragmentTransaction trans = mFragmentManager.beginTransaction();
        trans.add(container.getId(),fragment,"fragment:"+position);
        trans.commit();
        return fragment;
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
