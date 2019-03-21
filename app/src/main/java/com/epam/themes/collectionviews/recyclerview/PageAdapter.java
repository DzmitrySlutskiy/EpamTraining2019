package com.epam.themes.collectionviews.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.epam.cleancodetest.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PageAdapter extends RecyclerView.Adapter<PageAdapter.ViewHolder> {

    private final LayoutInflater mInflater;
    private List<Object> mItems = new ArrayList<Object>() {{

        for (int i = 0; i < 10; i++) {
            add(new Object());
        }
    }};


    public PageAdapter(final Context pContext) {
        mInflater = (LayoutInflater) pContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(mInflater.inflate(R.layout.layout_page, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int pIndex) {
//        abhsdjnasm(viewHolder, pIndex);
        ((TextView) viewHolder.itemView).setText("View #" + pIndex);
    }

    private void bindItem(@NonNull final ViewHolder viewHolder, int pIndex) {
        Object partiallyCorrectItem = mItems.get(pIndex);
        viewHolder.itemView.setTag(pIndex); //doesnt work after list modification

        ((TextView) viewHolder.itemView).setText("View #" + pIndex);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int adapterPosition = viewHolder.getAdapterPosition();
                Object correctItem = mItems.get(adapterPosition);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void updateItems(final List<Object> pItems) {
//        mItems = new ArrayList<>(pItems); no need to recreate list
        mItems.clear();
        mItems.addAll(pItems);
        /*
           notifyDataSetChanged();
           notifyItemRangeChanged();
           notifyItemMoved();
         */
        DiffUtil.calculateDiff(new DiffUtil.Callback() {

            @Override
            public int getOldListSize() {
                return 0;
            }

            @Override
            public int getNewListSize() {
                return 0;
            }

            @Override
            public boolean areItemsTheSame(int i, int i1) {
                return false;
            }

            @Override
            public boolean areContentsTheSame(int i, int i1) {
                return false;
            }
        }).dispatchUpdatesTo(this);
    }

    public void deleteByIndex(int i) {
        mItems.remove(i);
        notifyItemRemoved(i);
    }

    public void onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(mItems, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(mItems, i, i - 1);
            }
        }

        notifyItemMoved(fromPosition, toPosition);
    }

    public void onItemDismiss(int adapterPosition) {
        deleteByIndex(adapterPosition);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ViewHolder(final View view) {
            super(view);
        }
    }
}
