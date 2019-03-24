package com.epam.themes.collectionviews.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.epam.themes.backend.IWebService;
import com.epam.themes.backend.entities.BaseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class BaseAdapter<Entity extends BaseEntity> extends RecyclerView.Adapter<BaseAdapter.ViewHolder> {
    List<Entity> mEntities = new ArrayList<>();
    final LayoutInflater mInflater;
    final IWebService<Entity> mService;

    static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    BaseAdapter(final Context pContext, final IWebService<Entity> mService) {
        mInflater = (LayoutInflater) pContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mService = mService;
    }

    public void onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(mEntities, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(mEntities, i, i - 1);
            }
        }

        notifyItemMoved(fromPosition, toPosition);
    }

    public void onItemDismiss(int adapterPosition) {
        deleteByIndex(adapterPosition);
    }

    public void deleteByIndex(int i) {
        Long itemId = mEntities.get(i).getId();
        mEntities.remove(i);

        if (mService != null) mService.delete(itemId);

        notifyItemRemoved(i);
    }
}
