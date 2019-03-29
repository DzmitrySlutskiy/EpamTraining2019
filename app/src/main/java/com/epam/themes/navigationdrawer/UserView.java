package com.epam.themes.navigationdrawer;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
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

import java.util.Locale;
import java.util.Random;

public class UserView extends LinearLayout {
    private String SIX_DIGIT_FORMAT = "#%06d";

    private TextView userNameView;
    private TextView userEmailView;
    private ImageView userIcon;

    public UserView(Context context) {
        super(context);
        init();
    }

    public UserView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public UserView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public UserView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        setOrientation(VERTICAL);

        inflate(getContext(), R.layout.user_view, this);

        userIcon = findViewById(R.id.user_image);
        userNameView = findViewById(R.id.user_name);
        userEmailView = findViewById(R.id.user_email);
    }

    public void updateUserInfo(UserModel userModel) {
        userNameView.setText(userModel.getUsername());
        userEmailView.setText(userModel.getEmail());

        Drawable drawable = AppCompatResources.getDrawable(getContext(), userModel.getIcon());
        drawable.setColorFilter(new PorterDuffColorFilter(getRandomColor(), PorterDuff.Mode.MULTIPLY));
        userIcon.setImageDrawable(drawable);
    }

    private int getRandomColor() {
        String colorString = String.format(Locale.getDefault(), SIX_DIGIT_FORMAT, new Random().nextInt(999999));
        return Color.parseColor(colorString);
    }
}
