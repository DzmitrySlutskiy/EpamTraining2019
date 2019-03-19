package com.epam.themes.navigationdrawer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.epam.cleancodetest.R;

public class NavigationDrawerActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_navigation_drawer);

        drawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        setNavigationSelected(navigationView);

        setToolbar();

        beforeCreate(navigationView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setNavigationSelected(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        drawerLayout.closeDrawers();
                        replaceFragment(menuItem.getItemId());
                        return true;
                    }
                });
    }

    private void beforeCreate(NavigationView navigationView) {
        View headerView = navigationView.getHeaderView(0);
        final UserView userView = headerView.findViewById(R.id.user_view);
        final ImageView imageView = headerView.findViewById(R.id.user_image);
        final UserModel userModel = getUserModel();

        userView.updateUserInfo(userModel);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userView.updateUserInfo(userModel);
            }
        });
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_white);
        actionbar.setDisplayShowTitleEnabled(false);
    }

    private void replaceFragment(int menuItemId) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, getFragment(menuItemId))
                .commit();
    }

    private Fragment getFragment(int menuItemId) {
        NavigationDrawerFragment fragment = new NavigationDrawerFragment();
        Bundle bundle = new Bundle();
        int fragmentLayoutResId = getFragmentLayoutResId(menuItemId);
        bundle.putInt(NavigationDrawerFragment.LAYOUT_ID, fragmentLayoutResId);
        fragment.setArguments(bundle);
        return fragment;
    }

    private int getFragmentLayoutResId(int menuItemId) {
        int fragmentLayoutResId;
        switch (menuItemId) {
            case R.id.nav_images:
                fragmentLayoutResId = R.layout.fragment_navigation_drawer_photos;
                break;
            case R.id.nav_settings:
                fragmentLayoutResId = R.layout.fragment_navigation_drawer_settings;
                break;
            default:
                fragmentLayoutResId = R.layout.fragment_navigation_drawer_settings;
        }
        return fragmentLayoutResId;
    }

    private UserModel getUserModel() {
        final UserModel userModel = new UserModel();
        userModel.setUsername("Current user");
        userModel.setEmail("test@local.host");
        userModel.setIcon(R.drawable.ic_person_white);

        return userModel;
    }
}
