package com.epam.themes.backend.entities;

import android.support.annotation.DrawableRes;

public class Student extends BaseEntity {

    private String mName;
    private int mHwCount;

    @DrawableRes
    private int mIcon;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public int getHwCount() {
        return mHwCount;
    }

    public void setHwCount(int hwCount) {
        mHwCount = hwCount;
    }

    public int getIcon() {
        return mIcon;
    }

    public void setIcon(@DrawableRes int mIcon) {
        this.mIcon = mIcon;
    }
}
