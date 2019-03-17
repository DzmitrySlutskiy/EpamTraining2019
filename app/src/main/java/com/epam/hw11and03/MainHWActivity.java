package com.epam.hw11and03;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.epam.cleancodetest.R;
import com.epam.hw11and03.hw_fragments.FirstFragment;
import com.epam.hw11and03.hw_fragments.SecondFragment;
import com.epam.hw11and03.views.HeaderView;

public class MainHWActivity extends AppCompatActivity {
    private final String TAG = MainHWActivity.class.getSimpleName();
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private HeaderView headerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_hw);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_header_container);
        headerView = new HeaderView(this);
        navigationView.addHeaderView(headerView);


        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                headerView.updateColorIcon();
            }
        });

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        menuItem.setChecked(true);
                        switch (menuItem.getItemId()) {
                            case (R.id.nav_bike):
                                Log.d(TAG, "R.id.nav_bike");
                                setFragment(new FirstFragment());

                                break;
                            case (R.id.nav_audio):
                                Log.d(TAG, "R.id.nav_audio");
                                setFragment(new SecondFragment());

                                break;
                        }
                        drawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    private void setFragment(@NonNull Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container_frame_layout, fragment);
        transaction.commit();

    }
}