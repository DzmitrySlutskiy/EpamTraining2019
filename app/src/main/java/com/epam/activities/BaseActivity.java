package com.epam.activities;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.epam.cleancodetest.R;

public class BaseActivity extends Activity {

    @Override
    public void setContentView(final int layoutResID) {
        super.setContentView(layoutResID);

        setupListeners();
    }

    private void setupListeners() {
        findViewById(R.id.startAActivityView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                startActivity(new Intent(BaseActivity.this, AActivity.class));
            }
        });

        findViewById(R.id.startBActivityView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                startActivity(new Intent(BaseActivity.this, BActivity.class));
            }
        });

        findViewById(R.id.startCActivityView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                startActivity(new Intent(BaseActivity.this, CActivity.class));
            }
        });
    }
}
