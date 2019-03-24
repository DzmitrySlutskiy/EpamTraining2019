package com.epam.themes.collectionviews.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.epam.cleancodetest.R;
import com.epam.themes.backend.entities.Object;

import java.util.ArrayList;
import java.util.List;

public class PageAdapter extends BaseAdapter<Object> {
    public PageAdapter(final Context pContext) {
        super(pContext, null);
        fillItems();
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
        Object partiallyCorrectItem = mEntities.get(pIndex);
        viewHolder.itemView.setTag(pIndex); //doesnt work after list modification

        ((TextView) viewHolder.itemView).setText("View #" + pIndex);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int adapterPosition = viewHolder.getAdapterPosition();
                Object correctItem = mEntities.get(adapterPosition);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mEntities.size();
    }

    public void updateItems(final List<Object> pItems) {
//        mEntities = new ArrayList<>(pItems); no need to recreate list
        mEntities.clear();
        mEntities.addAll(pItems);
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

    private void fillItems() {
        mEntities = new ArrayList<Object>() {{
            for (int i = 0; i < 10; i++) {
                add(new Object());
            }
        }};
    }
}
