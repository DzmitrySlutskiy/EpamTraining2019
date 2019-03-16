package com.epam.themes.collectionviews;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.epam.cleancodetest.R;
import com.epam.themes.collectionviews.recyclerview.ItemTouchCallback;
import com.epam.themes.collectionviews.recyclerview.PageAdapter;
import com.epam.themes.collectionviews.viewpager.PageFragmentAdapter;

/**
 * TODO 1. ViewPager + TabLayout
 * TODO 2. ListView -> RecyclerView
 * TODO 3. Adapter, ViewHolder. DiffUtils, item position.
 * TODO 4. LayoutManager(s), ItemDecoration, ItemAnimator. Drag-and-Drop, Swipe-to-dismiss.
 * TODO 5. Pagination. Display collection from backend (stub data).
 */
public class CollectionViewsActivity extends AppCompatActivity {

    private TabLayout mTabView;
    private ViewPager mPagerView;
    private RecyclerView mRecyclerView;
    private PageAdapter adapter;

    @Override
    protected void onCreate(@Nullable final Bundle pSavedInstanceState) {
        super.onCreate(pSavedInstanceState);

        setContentView(R.layout.activity_collection_views);

        mTabView = findViewById(R.id.tabView);
        mPagerView = findViewById(R.id.pagerView);
        mPagerView.setAdapter(new PageFragmentAdapter(getSupportFragmentManager()));
        mTabView.setupWithViewPager(mPagerView);
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        adapter = new PageAdapter(this);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator() {
            @Override
            public boolean animateMove(RecyclerView.ViewHolder holder, int fromX, int fromY, int toX, int toY) {
                return super.animateMove(holder, fromX, fromY, toX, toY);
            }
        });
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
//        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
//
//
//            @Override
//            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
//                int position = parent.getChildAdapterPosition(view);
//
//                if (position == 0) {
//                    outRect.top += 20;
//                }
//
//                super.getItemOffsets(outRect, view, parent, state);
//            }
//        });
        new ItemTouchHelper(new ItemTouchCallback(mRecyclerView, adapter)).attachToRecyclerView(mRecyclerView);

        findViewById(R.id.studentsView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CollectionViewsActivity.this, StudentsActivity.class));
            }
        });
    }
}
