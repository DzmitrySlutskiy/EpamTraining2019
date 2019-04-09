package com.epam.themes.backend;

import android.os.Handler;
import android.os.Looper;

import com.epam.themes.backend.entities.Student;
import com.epam.themes.util.ICallback;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StudentsWebService implements IWebService<Student> {

    private List<Student> mStudents = new ArrayList<>();
    private Random mRandom = new Random();
    private Handler mHandler = new Handler(Looper.getMainLooper());

    private static ExecutorService executorService = Executors.newCachedThreadPool();

    {
        for (int i = 0; i < 1000; i++) {
            Student student = new Student();
            student.setId((long) i);
            student.setHwCount(1 + mRandom.nextInt(5));
            student.setName(String.valueOf(i));
            mStudents.add(student);
        }
    }

    @Override
    public void getEntities(ICallback<List<Student>> pCallback) {
    }

    @Override
    public void getEntities(final int pStartRange,
                            final int pEndRange,
                            IOperation<Student> poperation) {
        executorService.execute(poperation);
    }

    @Override
    public void removeEntity(Long pId) {

    }
}
