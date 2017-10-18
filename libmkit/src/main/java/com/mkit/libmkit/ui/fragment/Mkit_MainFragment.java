package com.mkit.libmkit.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.mkit.libmkit.R;
import com.mkit.libmkit.base.BaseFragment;
import com.mkit.libmkit.ui.adapter.SusPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WHF.Javas on 2017/10/13.
 */

public class Mkit_MainFragment extends BaseFragment {

    private SusPagerAdapter susPagerAdapter;
    private List<String> tabList;
    private List<Fragment> fragmentList;
    private ViewPager viewPager;
    private SlidingTabLayout tabLayout;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.mkit_activity, container, false);
    }

    @Override
    protected void initData(View view, Bundle savedInstanceState) {
        viewPager = (ViewPager)view. findViewById(R.id.viewPager);
        tabLayout = (SlidingTabLayout) view.findViewById(R.id.tabLayout);
        fragmentList = new ArrayList<>();
        addChannel();
        initFragment();
    }

    @Override
    protected void lazyLoad() {
    }

    private void addChannel() {
        tabList = new ArrayList<>();
        tabList.clear();
        tabList.add("For U");
        tabList.add("Sport");
        tabList.add("Fashion");
        tabList.add("India");
        tabList.add("Technology");
        tabList.add("Astrology");

    }

    private void initFragment() {
        for (int i = 0; i < tabList.size(); i++) {
            createFragment(fragmentList, tabList, i);
        }
        susPagerAdapter = new SusPagerAdapter(getChildFragmentManager(), mContext, tabList, fragmentList) {

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

    private void createFragment(List<Fragment> fragmentList, List<String> tabList, int flag) {
        Mkit_HomeListFragment fragment = Mkit_HomeListFragment.getInstance(flag);
        fragment.setTabTitle(tabList.get(tabList.size() - 1));
        fragmentList.add(fragment);
    }
}
