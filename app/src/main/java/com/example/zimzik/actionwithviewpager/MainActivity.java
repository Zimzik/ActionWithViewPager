package com.example.zimzik.actionwithviewpager;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<CharSequence> titles;
    private ViewPagerAdapter mViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: activity created");

        mViewPager = findViewById(R.id.view_pager);
        mTabLayout = findViewById(R.id.tab_layout);

        initViewPager();
        mViewPager.setOffscreenPageLimit(3);
    }

    private void initViewPager() {
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            initTabsForLandscape();
            initViewsForLandscape();
        } else {
            initTabs();
            initViews();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        switch (newConfig.orientation) {
            case Configuration.ORIENTATION_PORTRAIT:
                Log.d(TAG, "onConfigurationChanged: portrait mode, selected tab: " + mTabLayout.getSelectedTabPosition());
                initTabs();
                mViewPagerAdapter.addFragment(new FragmentA(), 0, "Fragment A");
                mTabLayout.setupWithViewPager(mViewPager);
                Log.d(TAG, "onConfigurationChanged: portrait mode, selected tab: " + mTabLayout.getSelectedTabPosition());
                switch (mTabLayout.getSelectedTabPosition()) {
                    case 0:
                        selectTab(1);
                        break;
                    case 1:
                        selectTab(2);
                        break;
                    case 2:
                        selectTab(3);
                        break;
                }
                break;
            case Configuration.ORIENTATION_LANDSCAPE:
                int selectedTab = mTabLayout.getSelectedTabPosition();
                Log.d(TAG, "onConfigurationChanged: landscape mode, selected tab: " + mTabLayout.getSelectedTabPosition());
                initTabsForLandscape();
                mViewPagerAdapter.removeFragment(0);
                mTabLayout.setupWithViewPager(mViewPager);
                Log.d(TAG, "onConfigurationChanged: landscape mode, selected tab: " + mTabLayout.getSelectedTabPosition());
                switch (selectedTab) {
                    case 1:
                        selectTab(0);
                        break;
                    case 2:
                        selectTab(1);
                        break;
                    case 3:
                        selectTab(2);
                        break;
                }
                break;
        }
    }

    private void initTabs() {
        titles = new ArrayList<>(Arrays.asList("Fragment A", "Fragment B", "Fragment C", "Fragment D"));
    }

    private void initTabsForLandscape() {
        titles = new ArrayList<>(Arrays.asList("Fragment B", "Fragment C", "Fragment D"));
    }

    private void initViews() {

        mViewPagerAdapter = new ViewPagerAdapter(
                getSupportFragmentManager(),
                new ArrayList<>(Arrays.asList(
                        new FragmentA(),
                        new FragmentB(),
                        new FragmentC(),
                        new FragmentD())
                ),
                titles
        );
        mViewPager.setAdapter(mViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initViewsForLandscape() {
        Log.d(TAG, "initViewsForLandscape: " + mViewPager.getAdapter());
        mViewPagerAdapter = new ViewPagerAdapter(
                getSupportFragmentManager(),
                new ArrayList<>(Arrays.asList(
                        new FragmentB(),
                        new FragmentC(),
                        new FragmentD()
                )),
                titles
        );
        mViewPager.setAdapter(mViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void selectTab(int newPosition) {
        mTabLayout.post(() -> mTabLayout.getTabAt(newPosition).select());
    }
}
