package com.epam.themes.uicomponents.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.InflateException;
import android.view.View;
import android.widget.RelativeLayout;

abstract public class CompoundRelativeLayout extends RelativeLayout implements ICompoundView {

    {
        final Context context = getContext();

        if (context == null) {
            throw new InflateException("Context was null before inflation");
        } else {
            View.inflate(context, getLayoutResId(), this);
            onViewInflated(context);
        }
    }

    public CompoundRelativeLayout(final Context pContext) {
        super(pContext);
    }

    public CompoundRelativeLayout(final Context pContext, final AttributeSet pAttrs) {
        super(pContext, pAttrs);
    }

    public CompoundRelativeLayout(final Context pContext, final AttributeSet pAttrs, final int pDefAttrs) {
        super(pContext, pAttrs, pDefAttrs);
    }
}
