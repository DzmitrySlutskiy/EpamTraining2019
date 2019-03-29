package com.epam.themes.collectionviews.recyclerview;

import android.os.Parcel;
import android.os.Parcelable;

public class StudentParcelable implements Parcelable {
    private Long studentId;
    private String studentName;
    private String studentEmail;
    private String studentAddress;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentAddress() {
        return studentAddress;
    }

    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }

    public StudentParcelable(Long studentId, String studentName, String studentEmail, String studentAddress){
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.studentAddress = studentAddress;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(studentId);
        dest.writeString(studentName);
        dest.writeString(studentEmail);
        dest.writeString(studentAddress);
    }

    public static final Parcelable.Creator<StudentParcelable> CREATOR = new Parcelable.Creator<StudentParcelable>() {

        public StudentParcelable createFromParcel(Parcel in) {
            return new StudentParcelable(in);
        }

        public StudentParcelable[] newArray(int size) {
            return new StudentParcelable[size];
        }
    };

    private StudentParcelable(Parcel in) {
        studentId = in.readLong();
        studentName = in.readString();
        studentEmail = in.readString();
        studentAddress = in.readString();
    }

    @Override
    public String toString() {
        return "Student{" + "id='" + studentId + '\'' + ", name='" + studentName + '\'' +
            ", email='" + studentEmail + '\'' + ", address='" + studentAddress + '\'' + '}';
    }
}
