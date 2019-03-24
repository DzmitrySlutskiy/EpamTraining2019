package com.epam.themes.backend;

import android.os.Handler;
import android.os.Looper;

import com.epam.cleancodetest.R;
import com.epam.themes.backend.entities.Student;
import com.epam.themes.util.ICallback;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.bloco.faker.Faker;

public class StudentsWebService implements IWebService<Student> {
    private String SPACE_STRING = " ";
    private static StudentsWebService instance;
    private List<Student> mStudents = new ArrayList<>();
    private Random mRandom = new Random();
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private Faker faker = new Faker();

    private StudentsWebService() {
        fillStudentsWithFakeData();
    }

    public static StudentsWebService getInstance() {
        if (instance == null) {
            instance = new StudentsWebService();
            return instance;
        }
        return instance;
    }

    @Override
    public Student create(final Student student) {
        Student studentToSave = new Student();
        studentToSave.setName(student.getName());
        studentToSave.setIcon(student.getIcon());
        studentToSave.setHwCount(student.getHwCount());
        studentToSave.setId(generateStudentId());
        mStudents.add(studentToSave);
        return studentToSave;
    }

    @Override
    public Student read(Long pId) {
        return getStudentById(pId);
    }

    @Override
    public boolean update(Student student) {
        Student studentToUpdate = getStudentById(student.getId());

        if (studentToUpdate == null) return false;

        studentToUpdate.setHwCount(student.getHwCount());
        studentToUpdate.setIcon(student.getIcon());
        studentToUpdate.setName(student.getName());
        return true;
    }

    @Override
    public void delete(Long pId) {
        Student student = getStudentById(pId);
        if (student != null) mStudents.remove(student);
    }

    @Override
    public void readAll(final ICallback<List<Student>> pCallback) {
        mHandler.postDelayed(makeRunnableWebResult(pCallback, mStudents), 1000);
    }

    @Override
    public void readAll(final int pStartRange,
                        final int pEndRange,
                        final ICallback<List<Student>> pCallback) {
        mHandler.postDelayed(makeRunnableWebResult(pCallback, getStudentByRange(pStartRange, pEndRange)), 1000);
    }

    private Student getStudentById(Long pId) {
        for (Student student : mStudents) {
            if (student.getId().equals(pId)) return student;
        }
        return null;
    }

    private List<Student> getStudentByRange(int fromIdx, int toIdx) {
        if (fromIdx < mStudents.size() && toIdx < mStudents.size()) {
            return mStudents.subList(fromIdx, toIdx);
        }
        if (fromIdx < mStudents.size()) {
            return mStudents.subList(fromIdx, mStudents.size());
        }
        return new ArrayList<>();
    }

    private void fillStudentsWithFakeData() {
        for (int i = 0; i < 15; i++) {
            Student student = new Student();
            student.setId((long) i);
            student.setHwCount(1 + mRandom.nextInt(5));
            student.setName(faker.name.firstName().concat(SPACE_STRING).concat(faker.name.lastName()));
            student.setIcon(R.drawable.ic_account_circle);
            mStudents.add(student);
        }
    }

    private Long generateStudentId() {
        return mRandom.nextLong();
    }

    private Runnable makeRunnableWebResult(final ICallback<List<Student>> pCallback, final List<Student> students) {
        return new Runnable() {
            @Override
            public void run() {
                pCallback.onResult(students);

            }
        };
    }
}
