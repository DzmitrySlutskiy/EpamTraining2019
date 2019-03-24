package com.epam.themes.backend.entities;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return mHwCount == student.mHwCount &&
                Objects.equals(mId, student.mId) &&
                Objects.equals(mName, student.mName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mId, mName, mHwCount);
    }
}
