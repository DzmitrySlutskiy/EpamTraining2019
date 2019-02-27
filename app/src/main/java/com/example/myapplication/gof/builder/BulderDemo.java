package com.example.myapplication.gof.builder;

import android.app.AlertDialog;
import android.content.Context;

public class BulderDemo {

    AlertDialog getDialog(final Context pContext) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(pContext);

        return builder
                .setTitle("Title")
                .setMessage("Demo")
                .create();
    }
}
