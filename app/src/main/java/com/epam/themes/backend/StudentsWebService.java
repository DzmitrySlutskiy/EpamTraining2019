package com.epam.themes.backend;

import android.os.Handler;
import android.os.Looper;
import com.epam.themes.backend.entities.Student;
import com.epam.themes.util.ICallback;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StudentsWebService implements IWebService<Student> {
    public static final String ADDRESS = "Address: Street ";
    public static final String NAME = "Name: ";
    public static final String EMAIL = "Email: ";

    private List<Student> students = new ArrayList<>();
    private Random random = new Random();
    private Handler handler = new Handler(Looper.getMainLooper());

    {
        for (int i = 0; i < 1000; i++) {
            Student student = new Student();
            student.setId((long) i);
            student.setAddress( ADDRESS + random.nextInt(555));
            student.setName(NAME + String.valueOf(i));
            student.setEmail(EMAIL + String.valueOf(i));
            students.add(student);
        }
    }

    @Override
    public void getEntities(ICallback<List<Student>> pCallback) {
    }

    @Override
    public void getEntities(final int pStartRange, final int pEndRange, final ICallback<List<Student>> pCallback) {
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                pCallback.onResult(students.subList(pStartRange, pEndRange));
            }
        }, 1000);
    }

    @Override
    public void removeEntity(final Long id) {
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                for(int i=0; i < students.size(); i++) {
                    if (students.get(i).getId() == id) {
                        students.remove(i);

                        break;
                    }
                }
            }
        }, 1000);
    }

    @Override
    public void updateEntityById(final Long id, final Student student) {
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                for(int i=0; i < students.size(); i++) {
                    if (students.get(i).getId() == id) {
                        students.set(i, student);

                        break;
                    }
                }
            }
        }, 1000);
    }
}
