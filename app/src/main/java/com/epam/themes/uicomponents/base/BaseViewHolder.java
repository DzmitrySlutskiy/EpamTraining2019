package com.epam.themes.uicomponents.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class BaseViewHolder<T extends View> extends RecyclerView.ViewHolder {

    public BaseViewHolder(@NonNull final T pItemView, final ClickListener clickListener) {
        super(pItemView);
        pItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(getAdapterPosition(),v);
            }
        });
    }

    public T getView() {
        return (T) itemView;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
    }
}
