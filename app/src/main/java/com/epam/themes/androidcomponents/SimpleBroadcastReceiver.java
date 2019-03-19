package com.epam.themes.androidcomponents;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class SimpleBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context pContext, final Intent pIntent) {
        Toast.makeText(pContext, "Intent received", Toast.LENGTH_SHORT).show();
    }
}
