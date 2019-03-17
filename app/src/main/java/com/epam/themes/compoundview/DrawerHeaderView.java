package com.epam.themes.compoundview;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.epam.cleancodetest.R;
import java.util.Random;

public class DrawerHeaderView extends LinearLayout {
    private ImageView userIcon;

    public DrawerHeaderView(Context context) {
        super(context);
    }

    public DrawerHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawerHeaderView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setIconClickListener(){
        userIcon = findViewById(R.id.user_image);
        userIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeIconColor();
            }
        });
    }

    private void changeIconColor() {
        Random random = new Random();
        int color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
        userIcon.setColorFilter(color);
    }
}
