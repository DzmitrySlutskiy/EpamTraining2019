package com.epam.themes.collectionviews.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

public class ItemTouchCallback extends ItemTouchHelper.SimpleCallback {

    private final PageAdapter mAdapter;
    private final RecyclerView mRecycler;

    public ItemTouchCallback(final RecyclerView pRecycler, final PageAdapter pAdapter) {
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.START | ItemTouchHelper.END);
        mAdapter = pAdapter;
        mRecycler = pRecycler;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView,
                          @NonNull RecyclerView.ViewHolder viewHolder,
                          @NonNull RecyclerView.ViewHolder viewHolder1) {
        mAdapter.onItemMove(viewHolder.getAdapterPosition(), viewHolder1.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int adapterPosition = viewHolder.getAdapterPosition();

        if (RecyclerView.NO_POSITION != adapterPosition) {
            mAdapter.onItemDismiss(adapterPosition);
        }
    }
}
