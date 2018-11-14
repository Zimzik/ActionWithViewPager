package com.example.zimzik.actionwithviewpager;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<CharSequence> titles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = findViewById(R.id.view_pager);
        mTabLayout = findViewById(R.id.tab_layout);

        //initViewPager();
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
    protected void onResume() {
        initViewPager();
        super.onResume();
    }

    private void initTabs() {
        titles = Arrays.asList("Fragment A", "Fragment B", "Fragment C", "Fragment D");
    }

    private void initTabsForLandscape() {
        titles = Arrays.asList("Fragment B", "Fragment C", "Fragment D");
    }

    private void initViews() {

        ViewPagerAdapter adapter = new ViewPagerAdapter(
                getSupportFragmentManager(),
                Arrays.asList(
                        new FragmentA(),
                        new FragmentB(),
                        new FragmentC(),
                        new FragmentD()
                ),
                titles
        );

        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initViewsForLandscape() {
        Log.d(TAG, "initViewsForLandscape: " + mViewPager.getAdapter());
        ViewPagerAdapter adapter = new ViewPagerAdapter(
                getSupportFragmentManager(),
                Arrays.asList(
                        new FragmentB(),
                        new FragmentC(),
                        new FragmentD()
                ),
                titles
        );
        mViewPager.setAdapter(adapter);
        mViewPager.getAdapter();
        adapter.notifyDataSetChanged();
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
