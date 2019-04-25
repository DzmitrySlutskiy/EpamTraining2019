package com.epam.themes.collectionviews.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.epam.cleancodetest.R;
import com.epam.themes.backend.TrainingImageLoader;
import com.epam.themes.uicomponents.base.BaseViewHolder;
import com.epam.themes.util.IImageLoader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private final IImageLoader mImageLoader;
    private final List<String> mItems = new ArrayList<>();

    private final LayoutInflater mLayoutInflater;

    public ImageAdapter(final Context pContext) {
        mImageLoader = new TrainingImageLoader(pContext);
        mLayoutInflater = (LayoutInflater) pContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull final ViewGroup pViewGroup, final int pI) {
        return new BaseViewHolder<>(mLayoutInflater.inflate(R.layout.adapter_item_image, pViewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final BaseViewHolder pViewHolder, final int pI) {
        mImageLoader.loadAndShow((ImageView) pViewHolder.itemView, mItems.get(pI));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void updateItems(final Collection<String> pItems) {
        mItems.clear();
        mItems.addAll(pItems);
        notifyDataSetChanged(); //TODO DiffUtils
    }
}
