package com.epam.themes.collectionviews;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.epam.cleancodetest.R;
import com.epam.themes.backend.entities.Student;

public class StudentView extends LinearLayout {

    private TextView studentNameView;
    private TextView studentHwCountView;
    private ImageView studentImageView;

    public StudentView(Context context) {
        super(context);
        init(context);
    }

    public StudentView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public StudentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public StudentView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(final Context context) {
        inflate(context, R.layout.student_view, this);

        studentNameView = findViewById(R.id.student_name);
        studentHwCountView = findViewById(R.id.student_hw_count);
        studentImageView = findViewById(R.id.student_image);
    }

    public void updateStudentInfo(final Student student) {
        studentNameView.setText(student.getName());
        studentHwCountView.setText(String.valueOf(student.getHwCount()));

        Drawable drawable = AppCompatResources.getDrawable(getContext(), student.getIcon());

        studentImageView.setImageDrawable(drawable);
    }
}
