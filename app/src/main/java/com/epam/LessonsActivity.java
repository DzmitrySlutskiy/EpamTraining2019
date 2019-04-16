package com.epam;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.epam.cleancodetest.R;
import com.epam.database.DatabaseHelper;
import com.epam.database.model.Tag;
import com.epam.database.model.UserDatabaseModel;
import com.epam.themes.collectionviews.CollectionViewsActivity;
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

import static com.epam.database.Constants.DatabaseConstants.DATABASE_VERSION;
import static com.epam.database.Constants.DatabaseConstants.TAG_TABLE_NAME;
import static com.epam.database.Constants.DatabaseConstants.USER_TABLE_NAME;

public class LessonsActivity extends AppCompatActivity {

    public static final String TAG = "LessonsActivity";
    private TextView counter;
    private CustomAsyncTask customAsyncTask;
    private Lock lock;
    private ExecutorService executorService = Executors.newCachedThreadPool();
    private ContentResolver mContentResolver;


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

        counter = findViewById(R.id.counter);
        final DatabaseObserver databaseObserver = new DatabaseObserver(new Handler());
        mContentResolver = getApplicationContext().getContentResolver();

    //    mContentResolver.registerContentObserver(new Uri.Builder().appendPath("content").build(), true, databaseObserver);
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
        final DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext(), null, DATABASE_VERSION);

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
                final ContentValues contentValues = new ContentValues();

                contentValues.put(Tag.VALUE, "Magic some");
                contentValues.put(Tag._ID, 1);
                contentValues.put(Tag.USER_ID, 1L);
                databaseHelper.insert(TAG_TABLE_NAME, contentValues);

                ContentValues userContentValues = new ContentValues();

                userContentValues.put(UserDatabaseModel._ID, 1);
                userContentValues.put(UserDatabaseModel.NAME, "First");
                userContentValues.put(UserDatabaseModel.PASSWORD, "First password");

                databaseHelper.insert(USER_TABLE_NAME, userContentValues);

                ContentValues user2ContentValues = new ContentValues();

                user2ContentValues.put(UserDatabaseModel._ID, 2);
                user2ContentValues.put(UserDatabaseModel.NAME, "Second");
                user2ContentValues.put(UserDatabaseModel.PASSWORD, "Second password");

                databaseHelper.insert(USER_TABLE_NAME, user2ContentValues);

                final Cursor joinCursor = databaseHelper.query("SELECT " + "u." + UserDatabaseModel.NAME + " AS " + "USERNAME" + ", "
                        + "t." + Tag.VALUE + " AS TAG, " +
                         "t." + Tag.USER_ID + " * 1000 AS USER_ID  " +
                        " FROM " +
                        USER_TABLE_NAME + " AS u LEFT JOIN " + TAG_TABLE_NAME + " AS t" + " on t." + Tag.USER_ID + " = " + "u." + UserDatabaseModel._ID);

                try {
                    if (joinCursor.moveToFirst()) {
                        final String tag = joinCursor.getString(joinCursor.getColumnIndex("TAG"));
                        final String userId = joinCursor.getString(joinCursor.getColumnIndex("USER_ID"));
                        final String username = joinCursor.getString(joinCursor.getColumnIndex("USERNAME"));

                        Log.d("Magic", "tag " + tag + " " + username + " " + userId);
                    }
                } finally {
                    joinCursor.close();
                }

            //    mContentResolver.notifyChange(new Uri.Builder().appendPath("content").build(), null);


                final Cursor query = databaseHelper.query("SELECT * FROM " + TAG_TABLE_NAME);

                try {
                    if (query.moveToFirst()) {
                        final String string = query.getString(query.getColumnIndex(Tag.VALUE));
                        Log.d("Magic", string);
                    }
                } finally {
                    query.close();
                }

                final long delete = databaseHelper.delete(USER_TABLE_NAME, null);
                Log.d("Magic", String.valueOf(delete));

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

    private class DatabaseObserver extends ContentObserver {

        /**
         * Creates a content observer.
         *
         * @param handler The handler to run {@link #onChange} on, or null if none.
         */
        public DatabaseObserver(final Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(final boolean selfChange, final Uri uri) {
            super.onChange(selfChange, uri);

            Log.d("Magic", uri.toString());
        }
    }
}
