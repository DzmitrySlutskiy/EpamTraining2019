package com.example.myapplication;

import android.util.Log;

public class GradleDemo {

    public static final String SERVER_ADDRESS = "http://" + BuildConfig.SERVER_PREFIX + "server.com/";

    void foo() {
        Log.d("demo", "demoVersion SERVER_ADDRESS " + SERVER_ADDRESS);
    }
}
