package com.epam.themes.util;

import com.epam.themes.backend.entities.Student;

public interface StudentAdapterCallback {

    void onStudentChange(Student student);

    void onStudentRemove(long id);
}
