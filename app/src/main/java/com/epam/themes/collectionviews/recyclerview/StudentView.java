package com.epam.themes.collectionviews.recyclerview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.epam.cleancodetest.R;


public class StudentView extends RelativeLayout {

    private TextView studentNameTextView;
    private TextView studentHwCountTextView;
    private ImageView studentAvatarImageView;

    public StudentView(Context context) {
        super(context);

        initialize(context);
    }

    public StudentView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initialize(context);
    }

    public StudentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initialize(context);
    }

    private void initialize(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_student, this);

        studentNameTextView = findViewById(R.id.studentNameTextView);
        studentHwCountTextView = findViewById(R.id.studentHwCountTextView);
        studentAvatarImageView = findViewById(R.id.studentAvatarImageView);
    }

    public StudentView setStudentName(String studentName) {
        studentNameTextView.setText(studentName);

        return this;
    }

    public StudentView setStudentHwCount(int completeHW) {
        studentHwCountTextView.setText(new StringBuilder()
                .append("homework done - ")
                .append(String.valueOf(completeHW)).toString());

        return this;
    }

    public StudentView setStudentAvatar(Drawable studentAvatar) {
        studentAvatarImageView.setImageDrawable(studentAvatar);

        return this;
    }
}
