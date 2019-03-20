package com.epam.themes.collectionviews.recyclerview;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;
import com.epam.cleancodetest.R;
import com.epam.themes.uicomponents.base.CompoundRelativeLayout;

public class StudentItemView extends CompoundRelativeLayout {
    private TextView nameView;
    private TextView emailView;
    private TextView addressView;
    private ImageView mAvatarView;

    private String attributeName;
    private String attributeEmail;
    private String attributeAddress;

    public StudentItemView(final Context pContext) {
        super(pContext);
    }

    public StudentItemView(final Context pContext, final AttributeSet pAttrs) {
        super(pContext, pAttrs);
        parseAttributes(pContext, pAttrs);
    }

    public StudentItemView(final Context pContext, final AttributeSet pAttrs, final int pDefAttrs) {
        super(pContext, pAttrs, pDefAttrs);
        parseAttributes(pContext, pAttrs);
    }

    @Override
    public void onViewInflated(@NonNull final Context pContext) {
        nameView = findViewById(R.id.nameView);
        emailView = findViewById(R.id.emailView);
        addressView = findViewById(R.id.addressView);
        mAvatarView = findViewById(R.id.avatarView);
    }

    public int getLayoutResId() {
        return R.layout.view_student_item;
    }

    public StudentItemView setName(final String name) {
        nameView.setText(name);
        return this;
    }

    public StudentItemView setEmail(final String email) {
        emailView.setText(email);
        return this;
    }

    public StudentItemView setAddress(final String address) {
        addressView.setText(address);
        return this;
    }

    private void parseAttributes(final Context pContext, final AttributeSet pAttrs) {
        final Resources.Theme theme = pContext.getTheme();
        final TypedArray styledAttributes = theme.obtainStyledAttributes(pAttrs, R.styleable.StudentView, 0, 0);

        try {
            attributeName = styledAttributes.getString(R.styleable.StudentView_studentName);
            attributeEmail = styledAttributes.getString(R.styleable.StudentView_studentEmail);
            attributeAddress = styledAttributes.getString(R.styleable.StudentView_studentAddress);

            nameView.setText(attributeName);
            emailView.setText(attributeEmail);
            addressView.setText(attributeAddress);
        } finally {
            styledAttributes.recycle();
        }
    }
}
