package com.epam.themes.collectionviews.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

public class StudentTouchCallback extends ItemTouchHelper.SimpleCallback {

    private final StudentsAdapter studentsAdapter;

    public StudentTouchCallback(final StudentsAdapter studentsAdapter) {
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.START | ItemTouchHelper.END);

        this.studentsAdapter = studentsAdapter;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView,
                          @NonNull RecyclerView.ViewHolder viewHolder,
                          @NonNull RecyclerView.ViewHolder target) {
        studentsAdapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());

        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int adapterPosition = viewHolder.getAdapterPosition();

        if (RecyclerView.NO_POSITION != adapterPosition) {
            if (direction == ItemTouchHelper.END) {
                studentsAdapter.onItemDismiss(adapterPosition);
            } else if (direction == ItemTouchHelper.START) {
                studentsAdapter.onItemChange(adapterPosition);
            }
        }
    }
}
