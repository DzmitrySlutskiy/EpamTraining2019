package com.epam;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import static com.epam.TestService.EXAMPLE_ACTION;
import static com.epam.TestService.EXAMPLE_ACTION_FOR_FINISH;


public class TestBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (EXAMPLE_ACTION.equals(intent.getAction())) {
            Toast.makeText(context, "song start to play", Toast.LENGTH_SHORT).show();
        } else if (EXAMPLE_ACTION_FOR_FINISH.equals(intent.getAction())) {
            Toast.makeText(context, "song finished", Toast.LENGTH_SHORT).show();
        }
    }
}
