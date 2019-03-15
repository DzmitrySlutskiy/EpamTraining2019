package com.epam.themes.collectionviews.viewpager;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PageFragmentAdapter extends FragmentPagerAdapter {

    public PageFragmentAdapter(final FragmentManager pFragmentManager) {
        super(pFragmentManager);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(final int pIndex) {
        return "Title #" + pIndex;
    }

    @Override
    public Fragment getItem(final int pIndex) {
        return PageFragment.newInstance("Fragment #" + pIndex);
    }

    @Override
    public int getCount() {
        return 3;
    }
}
