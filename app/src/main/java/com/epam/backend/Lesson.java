package com.epam.backend;

public class Lesson {

    private int mentorIcon;
    private int mentorName;
    private int theme;
    private int date;

    public Lesson(final int mentorIcon, final int mentorName, final int theme, final int date) {
        this.mentorIcon = mentorIcon;
        this.mentorName = mentorName;
        this.theme = theme;
        this.date = date;
    }

    public int getMentorIcon() {
        return mentorIcon;
    }

    public void setMentorIcon(final int mentorIcon) {
        this.mentorIcon = mentorIcon;
    }

    public int getMentorName() {
        return mentorName;
    }

    public void setMentorName(final int mentorName) {
        this.mentorName = mentorName;
    }

    public int getTheme() {
        return theme;
    }

    public void setTheme(final int theme) {
        this.theme = theme;
    }

    public int getDate() {
        return date;
    }

    public void setDate(final int date) {
        this.date = date;
    }
}
