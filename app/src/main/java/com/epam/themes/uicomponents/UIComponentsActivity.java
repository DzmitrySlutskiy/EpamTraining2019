package com.epam.themes.uicomponents;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.epam.cleancodetest.R;

public class UIComponentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui_components);

        getSupportActionBar().setTitle(R.string.theme_android_ui);
    }
}
