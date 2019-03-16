package com.epam.themes.uicomponents.base;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;

public interface ICompoundView {

    void onViewInflated(@NonNull final Context pContext);

    @LayoutRes
    int getLayoutResId();
}
