package com.epam.themes.backend.entities;

public class Student {

    private Long mId;
    private String mName;
    private int mHwCount;

    public Long getId() {
        return mId;
    }

    public Student setId(Long mId) {
        this.mId = mId;

        return this;
    }

    public String getName() {
        return mName;
    }

    public Student setName(String name) {
        this.mName = name;

        return this;
    }

    public int getHwCount() {
        return mHwCount;
    }

    public Student setHwCount(int hwCount) {
        mHwCount = hwCount;

        return this;
    }
}
