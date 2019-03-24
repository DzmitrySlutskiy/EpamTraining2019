package com.epam.themes.collectionviews;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.epam.cleancodetest.R;

public class StudentEditView extends LinearLayout {
    private EditText editStudentName;
    private EditText editStudentHwCount;

    public StudentEditView(Context context) {
        super(context);
        init(context);
    }

    public StudentEditView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public StudentEditView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public StudentEditView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(final Context context) {
        inflate(context, R.layout.edit_student_view, this);

        editStudentName = findViewById(R.id.edit_student_name);
        editStudentHwCount = findViewById(R.id.edit_student_hw_count);
    }

    public EditText updateStudentName(String name) {
        editStudentName.setText(name);
        return editStudentName;
    }

    public EditText updateStudentHwCount(int hwCount) {
        editStudentHwCount.setText(String.valueOf(hwCount));
        return editStudentHwCount;
    }
}
