package com.epam.themes;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.epam.cleancodetest.R;
import com.epam.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class LoaderActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<User>> {

    public static final String TAG = "LoaderActivity";
    private static Executor sExecutor = Executors.newCachedThreadPool();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_loader);
    }

    @Override
    protected void onStart() {
        super.onStart();

        final Handler handler
                = new Handler();
        sExecutor.execute(new Runnable() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        });
        //by default looper will contain reference to Looper from current thread
//        final Handler handler = new Handler(Looper.getMainLooper());
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        we are in UI
//                    }
//                });
//            }
//        }).start();
//        LoaderManager.getInstance(this).restartLoader(100000000,new Bundle(), this);
        Looper.prepare();
    }

    @NonNull
    @Override
    public Loader<List<User>> onCreateLoader(int id, @Nullable Bundle bundle) {
        Log.d(TAG, "onCreateLoader() called with: id = [" + id + "], bundle = [" + bundle + "]");
        return new AsyncTaskLoader<List<User>>(this) {
            @Nullable
            @Override
            public List<User> loadInBackground() {
                return new ArrayList<>();
            }

            @Nullable
            @Override
            protected List<User> onLoadInBackground() {
                return super.onLoadInBackground();
            }

            @Override
            protected void onStartLoading() {
                super.onStartLoading();

                forceLoad();
            }
        };
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<User>> loader, List<User> users) {
        Log.d(TAG, "onLoadFinished() called with: loader = [" + loader + "], users = [" + users + "]");

    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<User>> loader) {
        Log.d(TAG, "onLoaderReset() called with: loader = [" + loader + "]");
    }
}
