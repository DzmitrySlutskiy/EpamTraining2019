package com.epam.themes.backend;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.LruCache;
import android.widget.ImageView;

import com.epam.cleancodetest.R;
import com.epam.themes.androidcomponents.wrappers.BitmapDiskCache;
import com.epam.themes.androidcomponents.wrappers.IDiskCache;
import com.epam.themes.util.ICallback;
import com.epam.themes.util.IImageLoader;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TrainingImageLoader implements IImageLoader {

    private int mBadCounter = 0;
    private final OkHttpClient mClient = new OkHttpClient();
    private final Executor mExecutor = Executors.newCachedThreadPool();
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private final IDiskCache<String, Bitmap> mDiskCache;
    private final LruCache<String, Bitmap> mLruCache = new LruCache<String, Bitmap>((int) (Runtime.getRuntime().maxMemory() / 1024 / 5)) {

        @Override
        protected int sizeOf(final String key, final Bitmap value) {
            return value.getByteCount() / 1024;
        }
    };

    public TrainingImageLoader(final Context pContext) {
        mDiskCache = new BitmapDiskCache(pContext);
    }

    @Override
    public void loadAndShow(final ImageView pImageView, final String pUri) {
        final String uri;

        if (mBadCounter++ > 6) {
            uri = pUri.replace("a", "");
            mBadCounter = 0;
        } else {
            uri = pUri;
        }

        if (TextUtils.isEmpty(uri)) {
            return;
        }

        pImageView.setTag(uri);
        pImageView.setImageResource(R.drawable.default_placeholder);


        loadFromMemoryCache(uri, new ICallback<Bitmap>() {

            @Override
            public void onResult(final Bitmap pResult) {
                if (pResult == null) {
                    mExecutor.execute(new Runnable() {
                        @Override
                        public void run() {
                            loadFromDiskCache(uri, new ICallback<Bitmap>() {

                                @Override
                                public void onResult(final Bitmap pResult) {
                                    if (pResult == null) {
                                        try {
                                            loadFromNetwork(uri, new ICallback<Bitmap>() {

                                                @Override
                                                public void onResult(final Bitmap pResult) {
                                                    if (pResult == null) {
                                                        showErrorImage(pUri, pImageView);
                                                    } else {
                                                        showImage(pUri, pImageView, pResult); // From network
                                                    }
                                                }

                                                @Override
                                                public void onError(final Throwable throwable) {
                                                    showErrorImage(pUri, pImageView);
                                                }
                                            });
                                        } catch (IOException pE) {
                                            showErrorImage(pUri, pImageView);
                                        }
                                    } else {
                                        showImage(pUri, pImageView, pResult); //from disk cache
                                    }
                                }

                                @Override
                                public void onError(final Throwable throwable) {
                                    //showErrorImage(pImageView);
                                }
                            });
                        }
                    });
                } else {
                    showImage(pUri, pImageView, pResult); // from memory cache
                }
            }

            @Override
            public void onError(final Throwable throwable) {
                //showErrorImage(pImageView);
            }
        });
    }

    private void loadFromMemoryCache(final String pUri, final ICallback<Bitmap> pResult) {
        synchronized (mLruCache) {
            pResult.onResult(mLruCache.get(pUri));
        }
    }

    private void loadFromDiskCache(final String pUri, final ICallback<Bitmap> pBitmapICallback) {
        synchronized (mDiskCache) {
            pBitmapICallback.onResult(mDiskCache.load(pUri));
        }
    }

    private void loadFromNetwork(final String pUri, final ICallback<Bitmap> pCallback) throws IOException {
        final Request request = new Request.Builder().url(pUri).build();
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(final Request request, final IOException e) {
                pCallback.onResult(null);
            }

            @Override
            public void onResponse(final Response response) throws IOException {
                final Bitmap result = decodeImage(response);

                pCallback.onResult(result);
                putInMemoryCache(pUri, result);
                putInDiskCache(pUri, result);
            }
        });
    }

    private void putInMemoryCache(final String pUri, final Bitmap pResult) {
        synchronized (mLruCache) {
            mLruCache.put(pUri, pResult);
        }
    }

    private void putInDiskCache(final String pUri, final Bitmap pResult) {
        synchronized (mDiskCache) {
            mDiskCache.save(pUri, pResult);
        }
    }

    void showImage(final String pUri, final ImageView pImageView, final Bitmap pBitmap) {
        if (isBitmapShouldBeSet(pUri, pImageView)) {
            mHandler.post(new Runnable() {

                @Override
                public void run() {
                    pImageView.setBackground(null);
                    pImageView.setImageBitmap(pBitmap);
                }
            });
        }
    }

    void showErrorImage(final String pUri, final ImageView pImageView) {
        if (isBitmapShouldBeSet(pUri, pImageView)) {
            mHandler.post(new Runnable() {

                @Override
                public void run() {
                    pImageView.setImageResource(R.drawable.error_placeholder);
                }
            });
        }
    }

    private boolean isBitmapShouldBeSet(final String pUri, final ImageView pImageView) {
        return pImageView.getTag() == null || (pImageView.getTag() != null && pUri.equals(pImageView.getTag()));
    }

    private Bitmap decodeImage(final Response pResponse) throws IOException {
        return BitmapFactory.decodeStream(pResponse.body().byteStream());
    }
}
