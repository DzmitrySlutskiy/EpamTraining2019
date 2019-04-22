package com.epam.themes.util;

import android.widget.ImageView;

import com.epam.themes.backend.TrainingImageLoader;

public interface IImageLoader {

    void loadAndShow(final ImageView pImageView, final String pUri);

    class Utils {

        private static final IImageLoader INSTANCE = new TrainingImageLoader();

        public static IImageLoader getInstance() {
            return INSTANCE;
        }
    }
}
