package com.epam.themes.compoundview;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.epam.cleancodetest.R;

public class DrawerFragment extends Fragment {
    private static String PAGE_NUM_KEY = "pageNumber";
    private int pageNumber;

    public DrawerFragment() {
        super();
        pageNumber = 0;
    }

    public static DrawerFragment newInstance(int pageNum) {
        DrawerFragment fragment = new DrawerFragment();
        Bundle args = new Bundle();
        args.putInt(PAGE_NUM_KEY, pageNum);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle arguments = getArguments();

        if (arguments != null && arguments.containsKey(PAGE_NUM_KEY)){
            pageNumber = arguments.getInt(PAGE_NUM_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drawer, container, false);
        TextView viewPageNum = view.findViewById(R.id.fragment_label);
        viewPageNum.setText(getString(R.string.page) + String.valueOf(pageNumber));

        return view;
    }
}
