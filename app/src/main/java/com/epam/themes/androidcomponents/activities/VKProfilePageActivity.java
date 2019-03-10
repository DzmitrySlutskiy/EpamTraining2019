package com.epam.themes.androidcomponents.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.epam.cleancodetest.R;

public class VKProfilePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vkprofile_page);

        Toolbar toolbar = findViewById(R.id.vk_toolbar);
        setSupportActionBar(toolbar);
    }
}
