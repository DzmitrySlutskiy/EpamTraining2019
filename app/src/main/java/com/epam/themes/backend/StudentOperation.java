package com.epam.themes.backend;

import com.epam.themes.androidcomponents.AbstractOperation;
import com.epam.themes.backend.entities.Student;
import com.epam.themes.util.ICallback;

public class StudentOperation extends AbstractOperation<Student> {

    public StudentOperation(ICallback<Student> callback) {
        super(callback);
    }

    @Override
    public Student doInBgh() {
        return new Student();
    }
}
