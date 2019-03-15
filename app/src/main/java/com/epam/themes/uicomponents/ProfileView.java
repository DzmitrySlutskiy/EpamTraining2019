package com.epam.themes.uicomponents;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.epam.cleancodetest.R;
import com.epam.themes.compoundview.ProfileModel;

import java.util.ArrayList;
import java.util.List;

public class ProfileView extends LinearLayout {

    private TextView profileNameView;
    private ImageView profileIcon;
    private Button profileAdd;

    //#FF0042
    private List<String> colorList = new ArrayList<>();

    public ProfileView(Context context) {
        super(context);
        init();
    }

    public ProfileView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ProfileView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ProfileView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        setOrientation(VERTICAL);

        inflate(getContext(), R.layout.profile_view, this);

        profileIcon = findViewById(R.id.profile_image);
        profileNameView = findViewById(R.id.profile_name);
        profileAdd = findViewById(R.id.button_add);
    }

    public void updateUserInfo(ProfileModel profileModel) {
        profileNameView.setText(profileModel.getName());

        Drawable drawable = ContextCompat.getDrawable(getContext(), profileModel.getIcon());

        //Drawable drawable1 = AppCompatResources.getDrawable(getContext(), profileModel.getIcon());

        int color = Color.parseColor("#FF0042");

        profileIcon.setImageDrawable(drawable);
        profileIcon.setBackgroundColor(color);
        profileAdd.setVisibility(View.GONE);
    }
}