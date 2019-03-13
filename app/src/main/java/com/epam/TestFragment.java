package com.epam;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TestFragment extends Fragment {
    private static final String SAVED_KEY = "textToSave";

    EditText fragmentEditText;
    Button fragmentButton;
    TextView fragmentTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_1, container, false);

        fragmentEditText = (EditText) view.findViewById(R.id.fragment_edit_text);
        fragmentButton = (Button) view.findViewById(R.id.fragment_click_button);
        fragmentTextView = (TextView) view.findViewById(R.id.fragment_text_view);

        fragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTextView.setText(fragmentEditText.getText());
            }
        });

        if (savedInstanceState != null) {
            fragmentTextView.setText(savedInstanceState.getString(SAVED_KEY));
        }

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString(SAVED_KEY, fragmentEditText.getText().toString());
    }
}
