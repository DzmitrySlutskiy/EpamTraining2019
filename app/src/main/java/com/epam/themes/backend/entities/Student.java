package com.epam.themes.backend.entities;

public class Student {

    private Long mId;
    private String mName;
    private int mHwCount;

    public Long getId() {
        return mId;
    }

    public void setId(Long mId) {
        this.mId = mId;
    }

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
}
