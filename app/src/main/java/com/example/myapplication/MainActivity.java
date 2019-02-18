package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "Simple toast for feature 2", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "This is second branch second toast", Toast.LENGTH_SHORT).show();
    }
}
