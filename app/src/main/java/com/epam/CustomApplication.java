package com.epam;

import android.app.Application;

import com.epam.database.Constants;
import com.epam.database.DatabaseHelper;

public class CustomApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        new DatabaseHelper(getApplicationContext(), null, Constants.DatabaseConstants.DATABASE_VERSION);
    }
}
