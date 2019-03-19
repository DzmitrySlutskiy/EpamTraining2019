package com.epam.themes.uicomponents;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.epam.cleancodetest.R;
import com.epam.themes.uicomponents.base.CompoundRelativeLayout;


public class StudentView extends CompoundRelativeLayout {


    private String studentNameStr;
    private TextView studentAgeTextView;
    private TextView studentNameTextView;
    private ImageView studentAvatarView;

    private String attributeStudentName;
    private String attributeStudentAge;


    public StudentView(Context pContext) {
        super(pContext);
    }

    public StudentView(Context pContext, AttributeSet pAttrs) {
        super(pContext, pAttrs);
        parseAttributes(pContext, pAttrs);
    }

    public StudentView(Context pContext, AttributeSet pAttrs, int pDefAttrs) {
        super(pContext, pAttrs, pDefAttrs);
        parseAttributes(pContext, pAttrs);
    }


    @Override
    public void onViewInflated(@NonNull final Context pContext) {
        studentAgeTextView = findViewById(R.id.student_age_tv);
        studentNameTextView = findViewById(R.id.student_name_tv);
        studentAvatarView = findViewById(R.id.student_avatar_image);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.student_lesson;
    }

    @UiThread
    public StudentView setStudentName(final String studentName) {
        studentNameTextView.setText("name: " + studentName);
        setStudentNameStr(studentName);
        return this;
    }

    @UiThread
    public StudentView setStudentAge(final String studentAge) {
        studentAgeTextView.setText(studentAge);
        return this;
    }


    private void parseAttributes(final Context pContext, final AttributeSet pAttrs) {
        final Resources.Theme theme = pContext.getTheme();
        final TypedArray styledAttributes = theme.obtainStyledAttributes(pAttrs, R.styleable.StudentView, 0, 0);

        try {
            attributeStudentName = styledAttributes.getString(R.styleable.StudentView_studentName);
            attributeStudentAge = styledAttributes.getString(R.styleable.StudentView_studentAge);

            studentAgeTextView.setText(attributeStudentName);
            studentNameTextView.setText(attributeStudentAge);
        } finally {
            styledAttributes.recycle();
        }
    }

    public String getStudentNameStr() {
        return studentNameStr;
    }

    public void setStudentNameStr(String studentNameStr) {
        this.studentNameStr = studentNameStr;
    }
}
