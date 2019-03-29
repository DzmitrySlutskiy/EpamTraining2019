package com.epam.themes.navigationdrawer;

import android.support.annotation.DrawableRes;

public class UserModel {
    @DrawableRes
    private int icon;

    private String username;
    private String email;

    public int getIcon() {
        return icon;
    }

    public void setIcon(@DrawableRes int icon) {
        this.icon = icon;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
