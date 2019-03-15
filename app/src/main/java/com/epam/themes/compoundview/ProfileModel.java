package com.epam.themes.compoundview;

import android.support.annotation.DrawableRes;

public class ProfileModel {

    private String name;

    @DrawableRes
    private int icon;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(@DrawableRes int icon) {
        this.icon = icon;
    }
}
