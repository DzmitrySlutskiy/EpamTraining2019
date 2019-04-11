package com.epam.themes.collectionviews;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.epam.cleancodetest.R;
import com.epam.themes.backend.IWebService;
import com.epam.themes.backend.StudentsWebService;
import com.epam.themes.backend.entities.Student;
import com.epam.themes.collectionviews.recyclerview.ItemTouchCallback;
import com.epam.themes.collectionviews.recyclerview.StudentsAdapter;
import com.epam.themes.util.ICallback;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class StudentsActivity extends AppCompatActivity {
    public static final int PAGE_SIZE = 10;
    public static final int MAX_VISIBLE_ITEMS = 40;

    private boolean mIsLoading;
    private StudentsAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private final IWebService<Student> mWebService = StudentsWebService.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);

        final RecyclerView recyclerView = findViewById(android.R.id.list);

        setLayoutManager(recyclerView);
        setAdapter(recyclerView);
        setTouchHelper(recyclerView);
        setScrollListener(recyclerView);
        loadMoreItems(0, PAGE_SIZE, recyclerView);
    }

    @NotNull
    private RecyclerView.OnScrollListener getListener() {
        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int totalItemCount = mAdapter.getItemCount();

                if (totalItemCount < PAGE_SIZE || totalItemCount > MAX_VISIBLE_ITEMS) {
                    postponeToNextFrameLastViewAsLoading(recyclerView, false);

                    return;
                }

                int visibleItemCount = mLayoutManager.getChildCount();
                int firstVisibleItemPosition = mLayoutManager.findFirstVisibleItemPosition();

                if (!mIsLoading) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0) {
                        loadMoreItems(totalItemCount, totalItemCount + PAGE_SIZE, recyclerView);
                    }
                }
            }
        };
    }

    private void loadMoreItems(final int pStartPosition, final int pEndPosition, final RecyclerView recyclerView) {
        postponeToNextFrameLastViewAsLoading(recyclerView, true);
        mWebService.readAll(pStartPosition, pEndPosition, new ICallback<List<Student>>() {
            @Override
            public void onResult(List<Student> pResult) {
                if (!pResult.isEmpty()) mAdapter.addItems(pResult);

                postponeToNextFrameLastViewAsLoading(recyclerView, false);
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });
    }

    private void setAdapter(RecyclerView recyclerView) {
        mAdapter = new StudentsAdapter(this, mWebService);
        recyclerView.setAdapter(mAdapter);
    }

    private void setLayoutManager(RecyclerView recyclerView) {
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
    }

    private void setTouchHelper(RecyclerView recyclerView) {
        new ItemTouchHelper(new ItemTouchCallback(recyclerView, mAdapter)).attachToRecyclerView(recyclerView);
    }

    private void setScrollListener(RecyclerView recyclerView) {
        recyclerView.addOnScrollListener(getListener());
    }

    private void postponeToNextFrameLastViewAsLoading(RecyclerView recyclerView, final boolean state) {
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                mAdapter.setShowLastViewAsLoading(mIsLoading = state);
            }
        });
    }
}
