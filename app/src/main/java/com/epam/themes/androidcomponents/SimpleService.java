package com.epam.themes.androidcomponents;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

public class SimpleService extends Service {

    private SimpleBinder mSimpleBinder;
    private Object mResult;

    @Override
    public int onStartCommand(final Intent intent, final int flags, final int startId) {
        Toast.makeText(this, "Service was started", Toast.LENGTH_LONG).show();

        mResult = new Object();

        sendBroadcast(new Intent("com.epam.CUSTOM_ACTION") {

            {
                putExtra("RESULT", (Bundle) mResult);
            }
        });

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Toast.makeText(this, "Service was stopped", Toast.LENGTH_LONG).show();
    }

    @Override
    public IBinder onBind(final Intent intent) {
        if (mSimpleBinder == null) {
            mSimpleBinder = new SimpleBinder();
        }

        return mSimpleBinder;
    }

    public Object getResult() {
        return mResult;
    }

    public class SimpleBinder extends Binder {

        public SimpleService getService() {
            return SimpleService.this;
        }
    }
}
