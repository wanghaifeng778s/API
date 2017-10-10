package com.mkit.libmkit.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.mkit.libmkit.R;
import com.mkit.libmkit.ui.adapter.SusPagerAdapter;
import com.mkit.libmkit.ui.fragment.HomeListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WHF.Javas on 2017/10/9.
 */

public class MkitActivity extends FragmentActivity{


    private SusPagerAdapter susPagerAdapter;
    private Context mContext;
    private List<String> tabList, cidList;
    private List<Fragment> fragmentList;
    private ViewPager viewPager;
    private SlidingTabLayout tabLayout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mkit_activity);
        mContext=this;
        viewPager=(ViewPager) findViewById(R.id.viewPager);
        tabLayout=(SlidingTabLayout) findViewById(R.id.tabLayout);
        fragmentList = new ArrayList<>();
        addChannel();
        initFragment();
    }

    private void addChannel() {
        tabList = new ArrayList<>();
        cidList = new ArrayList<>();
        tabList.clear();
        cidList.clear();
        for (int i = 0; i < 2; i++) {
                tabList.add("woshi"+i);
                cidList.add("wo"+i);
        }
    }

    private void initFragment() {
        for (int i = 0; i < cidList.size(); i++) {
            if (cidList.get(i) != null) {
                createFragment(fragmentList,tabList);
            }
        }
        susPagerAdapter = new SusPagerAdapter(getSupportFragmentManager(), mContext, tabList, fragmentList) {

            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getItemPosition(Object object) {
                return POSITION_NONE;
            }

            @Override
            protected Fragment createItem(int position) {
                Fragment f;
                switch (position) {
                    default:
                        f = fragmentList.get(position);
                        break;
                }
                return f;
            }
        };
        viewPager.setAdapter(susPagerAdapter);
        viewPager.setOffscreenPageLimit(fragmentList.size());
        tabLayout.setViewPager(viewPager);
        tabLayout.onPageSelected(tabLayout.getCurrentTab());
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {

            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    private void createFragment(List<Fragment> fragmentList, List<String> tabList) {
        HomeListFragment fragment = new  HomeListFragment();
        fragment.setTabTitle(tabList.get(tabList.size() - 1));
        fragmentList.add(fragment);
    }


}
