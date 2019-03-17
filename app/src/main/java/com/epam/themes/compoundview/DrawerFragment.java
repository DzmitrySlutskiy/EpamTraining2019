package com.epam.themes.compoundview;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.epam.cleancodetest.R;

public class DrawerFragment extends Fragment {
    private String pageNumber;

    public static DrawerFragment newInstance(int pageNum) {
        DrawerFragment fragment = new DrawerFragment();
        fragment.pageNumber = String.valueOf(pageNum);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drawer, container, false);
        TextView viewPageNum = view.findViewById(R.id.fragment_label);
        viewPageNum.setText(getString(R.string.page) + pageNumber);

        return view;
    }
}
