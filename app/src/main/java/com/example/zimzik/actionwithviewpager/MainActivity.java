package com.example.zimzik.actionwithviewpager;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private CharSequence[] titles;
    private Fragment mFragmentA = new FragmentA();

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
    protected void onPause() {
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            if (fragment != null) {
                getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            }
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        initViewPager();
        super.onResume();
    }

    private void initTabs() {
        titles = new CharSequence[]{"Fragment A", "Fragment B", "Fragment C", "Fragment D"};
    }

    private void initTabsForLandscape() {
        titles = new CharSequence[]{"Fragment B", "Fragment C", "Fragment D"};
    }

    private void initViews() {
        mViewPager.setAdapter(
                new ViewPagerAdapter(
                        getSupportFragmentManager(),
                        Arrays.asList(
                                mFragmentA,
                                new FragmentB(),
                                new FragmentC(),
                                new FragmentD()
                        ),
                        titles
                ));
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initViewsForLandscape() {
        mViewPager.setAdapter(
                new ViewPagerAdapter(
                        getSupportFragmentManager(),
                        Arrays.asList(
                                new FragmentB(),
                                new FragmentC(),
                                new FragmentD()
                        ),
                        titles
                ));
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
