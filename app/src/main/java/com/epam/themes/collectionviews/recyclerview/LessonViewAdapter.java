package com.epam.themes.collectionviews.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.epam.themes.uicomponents.LessonView;
import com.epam.themes.uicomponents.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class LessonViewAdapter extends RecyclerView.Adapter<BaseViewHolder<LessonView>> {

    final List<Object> mItems = new ArrayList<>();

    @NonNull
    @Override
    public BaseViewHolder<LessonView> onCreateViewHolder(@NonNull final ViewGroup pParent,
                                                         final int pViewType) {
        return new BaseViewHolder<>(new LessonView(pParent.getContext()));
    }

    @Override
    public void onBindViewHolder(@NonNull final BaseViewHolder<LessonView> viewHolder,
                                 final int pIndex) {
        //TODO Bind data by index
        viewHolder
                .getView()
                .setLessonDate("14.03.2019")
                .setLessonTheme("Collection components");
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}

