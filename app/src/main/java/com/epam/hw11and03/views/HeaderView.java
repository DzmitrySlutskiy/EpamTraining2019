package com.epam.hw11and03.views;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.epam.cleancodetest.R;

import java.util.Random;

public class HeaderView extends LinearLayout {
    private TextView headerNameTextView;
    private TextView headerEmailTextView;
    private ImageView headerIcon;

    public HeaderView(Context context) {
        super(context);
        init();
    }

    public HeaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HeaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public HeaderView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }


    private void init() {
        setOrientation(VERTICAL);
        inflate(getContext(), R.layout.header_view, this);
        headerIcon = findViewById(R.id.header_image);
        headerEmailTextView = findViewById(R.id.header_email);
        headerNameTextView = findViewById(R.id.header_name);

    }

    public void updateColorIcon() {
        Random random = new Random();
        int color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
        headerIcon.setColorFilter(color);
    }
}