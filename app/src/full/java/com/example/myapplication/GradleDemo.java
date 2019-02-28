package com.example.myapplication;

import com.squareup.picasso.Picasso;

public class GradleDemo {

    public static final String SERVER_ADDRESS = "http://" + BuildConfig.SERVER_PREFIX + "server.com/";

    void foo() {
        Picasso picasso = Picasso.get();
    }
}
