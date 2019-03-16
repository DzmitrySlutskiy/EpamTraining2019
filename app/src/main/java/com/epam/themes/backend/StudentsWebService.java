package com.epam.themes.backend;

import android.os.Handler;
import android.os.Looper;

import com.epam.themes.backend.entities.Student;
import com.epam.themes.util.ICallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class StudentsWebService implements IWebService<Student> {

    private List<String> nameStudents = Arrays.asList(
            "Комар Андрей",
            "Aliaksei Shvants",
            "Maryia Senkevich",
            "Pavel Klimovich",
            "yahor shymanchyk",
            "Anton Liaskevich",
            "Pavel Klimovich",
            "VASILI ZAICEV",
            "Yahor Berdnikau",
            "MAKSIM ZHANHIALIOU",
            "ULADZISLAU SITSKO",
            "Aliaksandr Marozik",
            "Aliaksei Aleksiaichuk",
            "Goncharov Alexander",
            "MAKSIM NASALEVICH",
            "Vitali Kullikouski",
            "ALIAKSANDR LITSKEVICH",
            "Dzmitry Fedarovich",
            "Kiryl Shreyter",
            "Konopeshko Aleksei",
            "NATALLIA BONDARAVA",
            "ALIEKSANDR ZAKHARCHANKA",
            "ALIAKSEI HALAVACH",
            "Dzmitry Siarhei",
            "Maksim Siamashka",
            "Vladyslav Vsemirnov"
    );

    private List<Student> studentList = new ArrayList<>();
    private Random random = new Random();
    private Handler handler = new Handler(Looper.getMainLooper());

    {
        for (int i = 0; i < 26; i++) {
            Student student = new Student();
            student.setId((long) i);
            student.setHwCount(1 + random.nextInt(5));
            student.setName(nameStudents.get(i));
            student.setName(String.valueOf(i));
            studentList.add(student);
        }
    }

    @Override
    public void getEntities(ICallback<List<Student>> callback) {
        callback.onResult(studentList);
    }

    @Override
    public void getEntities(final int startRange,
                            final int endRange,
                            final ICallback<List<Student>> callback) {
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                if (startRange < studentList.size()) {
                    if (endRange > studentList.size()) {
                        callback.onResult(studentList.subList(startRange, studentList.size()));
                    } else {
                        callback.onResult(studentList.subList(startRange, endRange));
                    }
                } else {
                    callback.onResult(null);
                }
            }
        }, 3000);
    }

    @Override
    public void removeEntity(final Long id) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < studentList.size(); i++) {
                    if (studentList.get(i).getId().equals(id)) {
                        studentList.remove(i);

                        break;
                    }
                }
            }
        }, 3000);
    }

    @Override
    public void setHomework(final Long id, final int hwCount) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < studentList.size(); i++) {
                    if (studentList.get(i).getId().equals(id)) {
                        studentList.get(i).setHwCount(hwCount);

                        break;
                    }
                }
            }
        }, 3000);
    }
}
