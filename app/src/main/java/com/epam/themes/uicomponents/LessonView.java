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

public class LessonView extends CompoundRelativeLayout {

    private TextView mDateView;
    private TextView mThemeView;
    private ImageView mAvatarView;

    private String mAttributeDate;
    private String mAttributeTheme;

    public LessonView(final Context pContext) {
        super(pContext);
    }

    public LessonView(final Context pContext, final AttributeSet pAttrs) {
        super(pContext, pAttrs);

        parseAttributes(pContext, pAttrs);
    }

    public LessonView(final Context pContext, final AttributeSet pAttrs, final int pDefAttrs) {
        super(pContext, pAttrs, pDefAttrs);

        parseAttributes(pContext, pAttrs);
    }


    @Override
    public void onViewInflated(@NonNull final Context pContext) {
        mDateView = findViewById(R.id.dateView);
        mThemeView = findViewById(R.id.themeView);
        mAvatarView = findViewById(R.id.avatarView);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.view_lesson;
    }

    @UiThread
    public LessonView setLessonDate(final String pDate) {
        mDateView.setText(pDate);
        return this;
    }

    @UiThread
    public LessonView setLessonTheme(final String pTheme) {
        mThemeView.setText(pTheme);
        return this;
    }

    private void parseAttributes(final Context pContext, final AttributeSet pAttrs) {
        final Resources.Theme theme = pContext.getTheme();
        final TypedArray styledAttributes = theme.obtainStyledAttributes(pAttrs, R.styleable.LessonView, 0, 0);

        try {
            mAttributeDate = styledAttributes.getString(R.styleable.LessonView_lessonDate);
            mAttributeTheme = styledAttributes.getString(R.styleable.LessonView_lessonTheme);

            mDateView.setText(mAttributeDate);
            mThemeView.setText(mAttributeTheme);
        } finally {
            styledAttributes.recycle();
        }
    }
}
