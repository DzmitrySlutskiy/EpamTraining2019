package com.epam.themes.backend;

import android.os.Handler;
import android.os.Looper;

import com.epam.themes.backend.entities.Student;
import com.epam.themes.util.ICallback;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StudentsWebService implements IWebService<Student> {

    private static final String[] STUDENTS_NAMES = new String[]{
            "Aliaksei Shvants",
            "Maryia Senkevich",
            "Pavel Klimovich",
            "Yahor Shymanchyk",
            "Anton Liaskevich",
            "Yahor Berdnikau",
            "Maksim Zhanhialiou",
            "Uladzislau Sitsko",
            "Goncharov Alexander",
            "Maksim Nasalevich",
            "Vitali Kullikouski",
            "Aliaksandr Litskevich",
            "Kiryl Shreyter",
            "Konopeshko Aleksei",
            "Natallia Bondarava",
            "Aliaksei Halavach",
            "Maksim Siamashka",
            "Vladyslav Vsemirnov"};

    private List<Student> mStudents = new ArrayList<>();
    private Random mRandom = new Random();
    private Handler mHandler = new Handler(Looper.getMainLooper());

    {
        for (int i = 0; i < 1000; i++) {
            Student student = new Student();
            student.setId((long) i);
            student.setHwCount(1 + mRandom.nextInt(5));
            student.setName(STUDENTS_NAMES[mRandom.nextInt(STUDENTS_NAMES.length - 1)]);
            mStudents.add(student);
        }
    }

    @Override
    public void getEntities(ICallback<List<Student>> pCallback) {
    }

    @Override
    public void getEntities(final int pStartRange,
                            final int pEndRange,
                            final ICallback<List<Student>> pCallback) {
        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                pCallback.onResult(mStudents.subList(pStartRange, pEndRange));
            }
        }, 1000);
    }

    @Override
    public void removeEntity(Long pId) {
        mStudents.remove(pId);
    }

    @Override
    public void addEntity(Student entity) {
        mStudents.add(0, entity);
    }

    @Override
    public void setEntity(long id, Student entity) {
        mStudents.set((int) id, entity);
    }
}
