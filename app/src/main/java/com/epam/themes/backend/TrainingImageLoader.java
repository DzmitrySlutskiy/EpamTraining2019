package com.epam.themes.backend;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.LruCache;
import android.widget.ImageView;

import com.epam.cleancodetest.R;
import com.epam.themes.util.ICallback;
import com.epam.themes.util.IImageLoader;
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
    private final LruCache<String, Bitmap> mLruCache = new LruCache<String, Bitmap>((int) (Runtime.getRuntime().maxMemory() / 1024 / 2)) {

        @Override
        protected int sizeOf(final String key, final Bitmap value) {
            return value.getByteCount() / 1024;
        }
    };

    @Override
    public void loadAndShow(final ImageView pImageView, final String pUri) {
        final String uri;

        if (mBadCounter++ > 6) {
            uri = pUri.replace("a", "");
            mBadCounter = 0;
        } else {
            uri = pUri;
        }

        if (TextUtils.isEmpty(uri) || isLoadAlreadyStarted(uri, pImageView)) {
            return;
        }

        pImageView.setTag(uri);
        pImageView.setImageBitmap(null);
        pImageView.setImageResource(R.drawable.default_placeholder);

        mExecutor.execute(new Runnable() {

            @Override
            public void run() {
                loadFromMemoryCache(uri, new ICallback<Bitmap>() {

                    @Override
                    public void onResult(final Bitmap pResult) {
                        if (pResult == null) {
                            loadFromDiskCache(uri, new ICallback<Bitmap>() {

                                @Override
                                public void onResult(final Bitmap pResult) {
                                    if (pResult == null) {
                                        try {
                                            loadFromNetwork(uri, new ICallback<Bitmap>() {

                                                @Override
                                                public void onResult(final Bitmap pResult) {
                                                    if (pResult == null) {
                                                        showErrorImage(pImageView);
                                                    } else {
                                                        showImage(pImageView, pResult);
                                                    }
                                                }

                                                @Override
                                                public void onError(final Throwable throwable) {
                                                    showErrorImage(pImageView);
                                                }
                                            });
                                        } catch (IOException pE) {
                                            showErrorImage(pImageView);
                                        }
                                    } else {
                                        showImage(pImageView, pResult);
                                    }
                                }

                                @Override
                                public void onError(final Throwable throwable) {
                                    //showErrorImage(pImageView);
                                }
                            });
                        } else {
                            showImage(pImageView, pResult);
                        }
                    }

                    @Override
                    public void onError(final Throwable throwable) {
                        //showErrorImage(pImageView);
                    }
                });
            }
        });
    }

    private boolean isLoadAlreadyStarted(final String pUri, final ImageView pImageView) {
        if (pImageView.getTag() != null /*&& pImageView.getTag().equals(pUri)*/) {
            return true;
        }

        return false;
    }

    private void loadFromMemoryCache(final String pUri, final ICallback<Bitmap> pResult) {
        synchronized (mLruCache) {
            pResult.onResult(mLruCache.get(pUri));
        }
    }

    private void loadFromDiskCache(final String pUri, final ICallback<Bitmap> pBitmapICallback) {
        pBitmapICallback.onResult(null);
    }

    private void loadFromNetwork(final String pUri, final ICallback<Bitmap> pBitmapICallback) throws IOException {
        final Request request = new Request.Builder().url(pUri).build();
        final Response response = mClient.newCall(request).execute();

        final Bitmap result = decodeImage(response);

        putInMemoryCache(pUri, result);
        pBitmapICallback.onResult(result);
    }

    private void putInMemoryCache(final String pUri, final Bitmap pResult) {
        synchronized (mLruCache) {
            mLruCache.put(pUri, pResult);
        }
    }

    void showImage(final ImageView pImageView, final Bitmap pBitmap) {
        pImageView.post(new Runnable() {
            @Override
            public void run() {
                pImageView.setBackground(null);
                pImageView.setImageBitmap(pBitmap);
            }
        });
    }

    void showErrorImage(final ImageView pImageView) {
        pImageView.post(new Runnable() {
            @Override
            public void run() {
                pImageView.setImageResource(R.drawable.error_placeholder);
            }
        });
    }

    private Bitmap decodeImage(final Response pResponse) throws IOException {
        return BitmapFactory.decodeStream(pResponse.body().byteStream());
    }
}
