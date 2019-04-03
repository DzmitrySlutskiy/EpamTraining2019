package com.epam.themes.collectionviews.viewpager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.epam.cleancodetest.R;

public class PageFragment extends Fragment {

    private static String PAGE_NAME_KEY = "com.epam.themes.collectionviews.viewpager.PAGE_NAME_KEY";

    public static Fragment newInstance(final String pPageName) {
        final Bundle arguments = new Bundle();
        final PageFragment fragment = new PageFragment();

        arguments.putString(PAGE_NAME_KEY, pPageName);
        fragment.setArguments(arguments);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater pInflater,
                             @Nullable final ViewGroup pContainer,
                             @Nullable final Bundle pSavedInstanceState) {
        final Bundle arguments = getArguments();
        final View fragmentView = pInflater.inflate(R.layout.layout_page, pContainer, false);

        if (arguments != null && arguments.containsKey(PAGE_NAME_KEY)) {
            ((TextView) fragmentView.findViewById(R.id.pageNameView)).setText(arguments.getString(PAGE_NAME_KEY));
        }

        return fragmentView;
    }


}
