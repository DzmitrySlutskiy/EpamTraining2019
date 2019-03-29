package com.epam.themes.backend.entities;

public class Student {
    private Long studentId;
    private String studentName;
    private String studentEmail;
    private String studentAddress;

    public Long getId() {
        return studentId;
    }

    public void setId(Long id) {
        this.studentId = id;
    }

    public String getName() {
        return studentName;
    }

    public void setName(String name) {
        this.studentName = name;
    }

    public String getEmail() {
        return studentEmail;
    }

    public void setEmail(String email) {
        studentEmail = email;
    }

    public String getAddress() {
        return studentAddress;
    }

    public void setAddress(String address) {
        this.studentAddress = address;
    }
}
