package com.epam.themes.collectionviews;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.v7.content.res.AppCompatResources;
import android.widget.ImageView;
import android.widget.TextView;

import com.epam.cleancodetest.R;
import com.epam.themes.backend.entities.Student;
import com.epam.themes.uicomponents.base.CompoundRelativeLayout;

public class StudentView extends CompoundRelativeLayout {

    private ImageView studentIconImageView;
    private TextView studentNameTextView;
    private TextView studentHWCountTextView;

    private Student student;

    public StudentView(Context context) {
        super(context);
    }

    @Override
    public void onViewInflated(@NonNull Context pContext) {
        studentIconImageView = findViewById(R.id.student_icon);
        studentNameTextView = findViewById(R.id.student_name);
        studentHWCountTextView = findViewById(R.id.student_homeworks_done_count);

        studentIconImageView.setImageDrawable(AppCompatResources.getDrawable(getContext(), R.drawable.ic_account_circle));
    }

    @Override
    public int getLayoutResId() {
        return R.layout.view_student;
    }

    @UiThread
    public void setStudent(final Student student) {
        studentNameTextView.setText(student.getName());
        studentHWCountTextView.setText(String.valueOf(student.getHwCount()));
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

}
