package com.epam.themes.androidcomponents.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageButton;

import com.epam.cleancodetest.R;

import java.lang.reflect.Field;

public class VKProfilePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vkprofile_page);

        Toolbar toolbar = findViewById(R.id.vk_toolbar_root);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        toolbar.setLogo(R.drawable.ic_keyboard_backspace_white_24dp);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_vk_profile, menu);
        return true;
    }
}
