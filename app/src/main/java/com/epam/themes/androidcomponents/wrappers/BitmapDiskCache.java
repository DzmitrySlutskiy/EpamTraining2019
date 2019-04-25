package com.epam.themes.androidcomponents.wrappers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class BitmapDiskCache implements IDiskCache<String, Bitmap> {

    private final String mDiskCachePath;

    public BitmapDiskCache(final Context pContext) {
        mDiskCachePath = Environment.getExternalStorageDirectory().toString();
    }

    @Override
    public boolean save(final String pKey, final Bitmap pValue) {
        try {
            final File file = new File(mDiskCachePath, Uri.parse(pKey).getLastPathSegment());
            final FileOutputStream stream = new FileOutputStream(file);

            pValue.compress(Bitmap.CompressFormat.JPEG, 50, stream); //TODO Handle image size compression
            stream.flush();
            stream.close();

            return true;
        } catch (final IOException pEx) {
            Log.e("BitmapDiskCache", pEx.getMessage());
            return false;
        }
    }

    @Override
    public Bitmap load(final String pKey) {
        return BitmapFactory.decodeFile(new File(mDiskCachePath, Uri.parse(pKey).getLastPathSegment()).toString());
    }
}
