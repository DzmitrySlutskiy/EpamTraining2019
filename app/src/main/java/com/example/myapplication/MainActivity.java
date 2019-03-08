package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.github.andrei1993ak.mentoring.awesomemodule.AwesomeUtil;
import com.github.andrei1993ak.mentoring.awesomemodule.OldRepository;
import com.github.andrei1993ak.mentoring.networkmodule.OldApi;
import com.github.andrei1993ak.mentoring.repositorymodule.Repository;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "Simple toast for feature 2", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "This is second branch second toast", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(getClass().getSimpleName(), "onResume() called");

        AwesomeUtil.Companion.toSting(this);
//        String call2 = new Api().getCall();
        String call = new OldApi().getCall();
        String user = new Repository().getUser();
        String oldUser = new OldRepository().getUser();
        new GradleDemo().foo();
    }
}
