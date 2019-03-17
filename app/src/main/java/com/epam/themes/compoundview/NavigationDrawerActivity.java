package com.epam.themes.compoundview;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import com.epam.cleancodetest.R;

public class NavigationDrawerActivity extends AppCompatActivity {
    static final int PAGES_NUM = 2;

    private DrawerLayout drawerLayout;
    private ViewPager pager;
    private PagerAdapter pagerAdapter;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);

        Toolbar toolbar = findViewById(R.id.toolbar_navigation_drawer_root);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle(R.string.theme_navigation_drawer);
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black);

        pager = (ViewPager) findViewById(R.id.view_pager);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        pagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);
        setPagerListener();
        setNavigationListener();
        DrawerHeaderView drawerHeaderView = navigationView.getHeaderView(0).findViewById(R.id.drawer_header_root);
        drawerHeaderView.setIconClickListener();
    }

    private class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

        public SimpleFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return DrawerFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return PAGES_NUM;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);

                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void swapFragments(int callerId) {
        int item = callerId == R.id.navigate_fragment_one ? 0 : 1;
        pager.setCurrentItem(item);
    }

    private void setPagerListener() {
        if (pager == null) {
            return;
        }

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state != ViewPager.SCROLL_STATE_IDLE) {
                    return;
                }

                navigationView.getMenu().getItem(pager.getCurrentItem()).setChecked(true);
            }
        });
    }

    private void setNavigationListener() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                drawerLayout.closeDrawers();
                swapFragments(menuItem.getItemId());

                return true;
            }
        });
    }
}

