package com.neil.parallaxsplash;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @author nzbao
 * @CreateTime 2018/1/15
 * @Desc
 */
public class PaViewPagerAdapter extends FragmentPagerAdapter {
    private List<ParallaxFragment> fragments;
    public PaViewPagerAdapter(FragmentManager fm,List<ParallaxFragment> fragments) {
        super(fm);
        this.fragments=fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
