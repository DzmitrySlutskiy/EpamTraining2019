package com.epam;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

public class TestService extends Service {
    public static final String EXAMPLE_ACTION = "aaa.EXAMPLE_ACTION";
    public static final String EXAMPLE_ACTION_FOR_FINISH = "aaa.FOR_FINISH_ACTION";
    public static final String SENT_TEXT = "aaa.SENT_TEXT";

    private MyBinder myBinder;
    private MediaPlayer ambientMediaPlayer;

    @Override
    public void onCreate() {
        ambientMediaPlayer = MediaPlayer.create(this, R.raw.nothing_else_matters_julia_westlin);
        ambientMediaPlayer.setLooping(true);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Intent myIntent = new Intent(EXAMPLE_ACTION);
        myIntent.putExtra(SENT_TEXT, "This text was sent by service");
        sendBroadcast(myIntent);
        ambientMediaPlayer.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ambientMediaPlayer.stop();
        sendBroadcast(new Intent(EXAMPLE_ACTION_FOR_FINISH));
    }

    @Override
    public IBinder onBind(Intent intent) {
        if (myBinder == null) {
            myBinder = new MyBinder();
        }

        return myBinder;
    }

    private class MyBinder extends Binder {
    }
}
