package com.epam;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.epam.cleancodetest.R;
import com.epam.activities.AActivity;
import com.epam.activities.BActivity;
import com.epam.activities.CActivity;

public class MainActivity extends AppCompatActivity {

    private Intent mIntent;
    private Intent mBroadcastIntent = new Intent("com.epam.CUSTOM_ACTION");
    private IntentFilter mIntentFilter = new IntentFilter("com.epam.CUSTOM_ACTION");
    private ServiceConnection mServiceConnection = new SimpleServiceConnection();
    private SimpleBroadcastReceiver mReceiver = new SimpleBroadcastReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mIntent = new Intent(this, SimpleService.class);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragmentView, new SimpleFragment())
                .commit();

        findViewById(R.id.viewServiceStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                bindService(mIntent, mServiceConnection, BIND_AUTO_CREATE);
            }
        });

        findViewById(R.id.viewServiceStop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                unbindService(mServiceConnection);
            }
        });

        findViewById(R.id.viewSendBroadcast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                sendBroadcast(mBroadcastIntent);
            }
        });

        findViewById(R.id.startAActivityView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                startActivity(new Intent(MainActivity.this, AActivity.class));
            }
        });

        findViewById(R.id.startBActivityView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                startActivity(new Intent(MainActivity.this, BActivity.class));
            }
        });

        findViewById(R.id.startCActivityView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                startActivity(new Intent(MainActivity.this, CActivity.class));
            }
        });
    }

    @Override
    protected void onNewIntent(final Intent intent) {
        super.onNewIntent(intent);

        intent.getStringExtra("TEST");
    }

    @Override
    protected void onStart() {
        super.onStart();

        registerReceiver(mReceiver, mIntentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();

        unregisterReceiver(mReceiver);
    }

    void handleResult(final Object pResult) {
        //TODO: Result handling
    }

    class SimpleServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(final ComponentName name, final IBinder pBinder) {
            if (pBinder instanceof SimpleService.SimpleBinder) {
                final SimpleService service = ((SimpleService.SimpleBinder) pBinder).getService();
                final Object result = service.getResult();

                handleResult(result);
            }
        }

        @Override
        public void onServiceDisconnected(final ComponentName name) {

        }
    }
}
