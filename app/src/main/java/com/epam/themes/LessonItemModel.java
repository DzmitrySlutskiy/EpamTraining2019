package com.epam.themes;

import android.support.annotation.DrawableRes;

public class LessonItemModel {
    private final int lessonTheme;
    private final int lessonDate;
    private final int mentorName;

    @DrawableRes
    private final int avatar;

    public LessonItemModel(int lessonTheme, int lessonDate, int mentorName, int avatar) {
        this.lessonTheme = lessonTheme;
        this.lessonDate = lessonDate;
        this.mentorName = mentorName;
        this.avatar = avatar;
    }

    public int getLessonTheme() {
        return lessonTheme;
    }

    public int getLessonDate() {
        return lessonDate;
    }

    public int getMentorName() {
        return mentorName;
    }

    public int getAvatar() {
        return avatar;
    }
}
