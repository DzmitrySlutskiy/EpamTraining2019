package com.epam.themes.backend;

import com.epam.themes.util.ICallback;

import java.util.List;

public interface IImageWebService {

    void loadImages(final int pQuantity, final ICallback<List<String>> pResult);

    class Utils {

        private static final IImageWebService INSTANCE = new CatsWebService();

        public static IImageWebService getInstance() {
            return INSTANCE;
        }
    }
}
