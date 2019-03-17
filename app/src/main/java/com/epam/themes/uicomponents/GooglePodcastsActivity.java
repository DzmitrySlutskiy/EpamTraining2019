package com.epam.themes.uicomponents;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.epam.cleancodetest.R;

public class GooglePodcastsActivity extends AppCompatActivity {
    GooglePodcastsFragmentAdapter googlePodcastsFragmentAdapter;
    ViewPager googlePodcastsPager;
    static final int NUM_ITEMS = 3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_podcasts);
        setSupportActionBar((Toolbar) findViewById(R.id.google_podcasts_toolbar));

        googlePodcastsFragmentAdapter = new GooglePodcastsFragmentAdapter(getSupportFragmentManager());
        googlePodcastsPager = findViewById(R.id.google_podcasts_pager);
        googlePodcastsPager.setAdapter(googlePodcastsFragmentAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_google_podcasts_top, menu);
        return true;
    }

    public static class GooglePodcastsFragmentAdapter extends FragmentPagerAdapter {
        private String tabTitles[] = new String[]{"New episodes", "In progress", "Downloads"};
        GooglePodcastsFragmentAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        @Override
        public Fragment getItem(int position) {
            return new GooglePodcastFragment();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
    }
}
