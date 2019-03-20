package com.epam.themes.cleancode;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.epam.cleancodetest.R;

public class CleanCodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clean_code);

        getSupportActionBar().setTitle(R.string.theme_clean_code);
    }
}
