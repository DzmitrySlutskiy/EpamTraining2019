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

import java.util.ArrayList;
import java.util.List;

public class ImageLoaderActivity extends AppCompatActivity {

    public static final String URIS = "URIs";

    private View mProgressView;
    private final IImageWebService mImageWebService = IImageWebService.Utils.getInstance();
    private ArrayList<String> mItems = new ArrayList<>();
    private ImageAdapter mAdapter;

    @Override
    protected void onCreate(final Bundle pSavedInstanceState) {
        super.onCreate(pSavedInstanceState);

        setContentView(R.layout.activity_image_loader);

        mAdapter = new ImageAdapter(this);
        final RecyclerView recyclerView = findViewById(R.id.recyclerView);
        mProgressView = findViewById(android.R.id.progress);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(mAdapter);
        showProgress();

        if (pSavedInstanceState != null && pSavedInstanceState.containsKey(URIS)) {
            mAdapter.updateItems(pSavedInstanceState.getStringArrayList(URIS));
        } else {
            mImageWebService.loadImages(50, new ICallback<List<String>>() {

                @Override
                public void onResult(final List<String> pResult) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mItems.clear();
                            mItems.addAll(pResult);
                            mAdapter.updateItems(pResult);
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
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putStringArrayList(URIS, mItems);
    }

    @Override
    protected void onRestoreInstanceState(final Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState != null && savedInstanceState.containsKey(URIS)) {
            mAdapter.updateItems(savedInstanceState.getStringArrayList(URIS));
        }
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
