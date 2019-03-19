package com.epam.themes.backend.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class Student implements Parcelable {

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };
    private Long mId;
    private String mName;
    private int mHwCount;

    public Student() {
    }

    protected Student(Parcel in) {
        if (in.readByte() == 0) {
            mId = null;
        } else {
            mId = in.readLong();
        }
        mName = in.readString();
        mHwCount = in.readInt();
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (mId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(mId);
        }
        dest.writeString(mName);
        dest.writeInt(mHwCount);
    }
}
