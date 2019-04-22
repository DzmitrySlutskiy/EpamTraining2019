package com.epam.themes.collectionviews;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.epam.cleancodetest.R;
import com.epam.themes.backend.IImageWebService;
import com.epam.themes.collectionviews.recyclerview.ImageAdapter;
import com.epam.themes.util.ICallback;

import java.util.List;

public class ImageLoaderActivity extends AppCompatActivity {

    private View mProgressView;
    private final IImageWebService mImageWebService = IImageWebService.Utils.getInstance();

    @Override
    protected void onCreate(final Bundle pSavedInstanceState) {
        super.onCreate(pSavedInstanceState);

        setContentView(R.layout.activity_image_loader);

        final ImageAdapter adapter = new ImageAdapter(this);
        final RecyclerView recyclerView = findViewById(R.id.recyclerView);
        mProgressView = findViewById(android.R.id.progress);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(adapter);
        showProgress();
        mImageWebService.loadImages(50, new ICallback<List<String>>() {

            @Override
            public void onResult(final List<String> pResult) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.updateItems(pResult);
                        hideProgress();
                    }
                });
            }

            @Override
            public void onError(final Throwable throwable) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hideProgress();
                    }
                });
            }
        });
    }

    private void hideProgress() {
        if (mProgressView.getVisibility() != View.GONE) {
            mProgressView.setVisibility(View.GONE);
        }
    }

    private void showProgress() {
        if (mProgressView.getVisibility() != View.VISIBLE) {
            mProgressView.setVisibility(View.VISIBLE);
        }
    }
}
