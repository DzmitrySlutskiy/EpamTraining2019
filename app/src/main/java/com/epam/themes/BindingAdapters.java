package com.epam.themes;

import android.databinding.BindingAdapter;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

public class BindingAdapters {
    @BindingAdapter("app:srcVector")
    public static void setSrcVector(ImageView view, @DrawableRes int drawable) {
        view.setImageResource(drawable);
    }
}
