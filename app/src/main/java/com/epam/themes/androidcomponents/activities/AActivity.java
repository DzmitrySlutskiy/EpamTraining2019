package com.epam.themes.androidcomponents.activities;

import android.os.Bundle;

import com.epam.cleancodetest.R;

public class AActivity extends BaseActivity {

    @Override
    protected void onCreate( final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_a);
    }
}
