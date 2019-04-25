package com.epam;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.epam.cleancodetest.R;
import com.epam.themes.collectionviews.CollectionViewsActivity;
import com.epam.themes.collectionviews.ImageLoaderActivity;
import com.epam.themes.compoundview.CompoundViewActivity;
import com.epam.themes.uicomponents.UIComponentsActivity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LessonsActivity extends AppCompatActivity {

    public static final String TAG = "LessonsActivity";
    public static final int RC_IMAGE_LOADER_ACTIVITY = 135;
    private TextView counter;
    private CustomAsyncTask customAsyncTask;
    private Lock lock;
    private ExecutorService executorService = Executors.newCachedThreadPool();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons);

        findViewById(R.id.lesson_components_root_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLesson(UIComponentsActivity.class);
            }
        });

        findViewById(R.id.lesson_compound_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLesson(CompoundViewActivity.class);
            }
        });

        findViewById(R.id.collectionComponentsView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLesson(CollectionViewsActivity.class);
            }
        });

        findViewById(R.id.imageLoaderView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(LessonsActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, RC_IMAGE_LOADER_ACTIVITY);
                    } else {
                        openLesson(ImageLoaderActivity.class);
                    }
                } else {
                    openLesson(ImageLoaderActivity.class);
                }
            }
        });

        counter = findViewById(R.id.counter);
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, @Nullable final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_IMAGE_LOADER_ACTIVITY && resultCode == RESULT_OK) {
            openLesson(ImageLoaderActivity.class);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        lock = new ReentrantLock();
        try {
            lock.lock();

        } finally {
            lock.unlock();
        }

        synchronized (LessonsActivity.class) {

        }

        new MyThread1().start();
//
        new MyThread2().start();

        customAsyncTask = new CustomAsyncTask(this);
        customAsyncTask.execute(0, 1000);


        final Future<Integer> future1 = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Log.d(TAG, "future1.call() called fall to sleep");
                safeSleep(5000);
                Log.d(TAG, "future1.call() called done. do return");

                return 1000;
            }
        });

        final Future<Integer> future2 = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Log.d(TAG, "future2.call() called fall to sleep");
                safeSleep(1000);
                Log.d(TAG, "future2.call() called done. do return");

                return 100;
            }
        });

        final Future<Integer> future3 = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Log.d(TAG, "future3.call() called fall to sleep");
                safeSleep(5000);
                Log.d(TAG, "future3.call() called done. do return");


                return 10;
            }
        });

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.d(TAG, "run() called: " + (future1.get() + future2.get() + future3.get()));
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        //synchronized collection wrappers
        List<Object> syncList = Collections.synchronizedList(new ArrayList<>());
        Map<Object, Object> syncMap = Collections.synchronizedMap(new HashMap<>());
        //etc
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();
        //or CopyOnWriteArraySet and many other from java.util.concurrent
    }

    class MyThread1 extends Thread {
        MyThread1() {
            super(new Runnable() {
                @Override
                public void run() {
                    Log.d(TAG, "T1 try lock");
                    lock.lock();
                    Log.d(TAG, "T1 locked");
                    while (true) {
                        safeSleep(10000);
                    }
                }
            }, "MyThread1");
        }
    }

    class MyThread2 extends Thread {
        MyThread2() {
            super(new Runnable() {
                @Override
                public void run() {
                    Log.d(TAG, "T2 try lock");
                    lock.lock();
                    Log.d(TAG, "T2 locked");
                    while (true) {
                        safeSleep(10000);
                    }
                }
            }, "MyThread2");
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        //   customAsyncTask.cancel(true);
    }

    private void openLesson(Class<? extends Activity> lessonActivityClass) {
        startActivity(new Intent(this, lessonActivityClass));
    }

    private static class CustomAsyncTask extends AsyncTask<Integer, Integer, Integer> {

        WeakReference<LessonsActivity> weakRefActivity;
        private int internalCounter;

        public CustomAsyncTask(LessonsActivity activity) {
            weakRefActivity = new WeakReference<>(activity);
        }

        @Override
        protected Integer doInBackground(Integer... voids) {
            for (internalCounter = voids[0]; internalCounter < voids[1]; internalCounter++) {
                publishProgress(internalCounter);
                Log.d(TAG, "doInBackground() called with: internalCounter = [" + internalCounter + "] " + isMainThread());
                safeSleep(1000);
                if (isCancelled()) {
                    break;
                }
            }

            return internalCounter;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d(TAG, "onPreExecute() called " + isMainThread());
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            Log.d(TAG, "onPostExecute() called with: integer = [" + integer + "] " + isMainThread());
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            LessonsActivity lessonsActivity = weakRefActivity.get();
            if (lessonsActivity != null) {
                lessonsActivity.counter.setText(values[0].toString());
            }
        }

        @Override
        protected void onCancelled(Integer integer) {
            super.onCancelled(integer);
            Log.d(TAG, "onCancelled() called with: integer = [" + integer + "] " + isMainThread());
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            Log.d(TAG, "onCancelled() called: " + isMainThread());
        }
    }

    private static void safeSleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            Log.e(TAG, "safeSleep: ", e);
        }
    }

    static boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }
}
