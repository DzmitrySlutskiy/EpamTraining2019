package com.epam.themes.collectionviews.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

public class ItemStudentTouchCallback extends ItemTouchHelper.SimpleCallback {

    private final StudentsAdapter studentsAdapter;

    public ItemStudentTouchCallback(final StudentsAdapter studentsAdapter) {
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.START | ItemTouchHelper.END);
        this.studentsAdapter = studentsAdapter;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView,
                          @NonNull RecyclerView.ViewHolder viewHolder,
                          @NonNull RecyclerView.ViewHolder viewHolder1) {
        studentsAdapter.onItemMove(viewHolder.getAdapterPosition(), viewHolder1.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int adapterPosition = viewHolder.getAdapterPosition();

        if (RecyclerView.NO_POSITION != adapterPosition) {
            studentsAdapter.onItemDismiss(adapterPosition);
        }
    }
}
