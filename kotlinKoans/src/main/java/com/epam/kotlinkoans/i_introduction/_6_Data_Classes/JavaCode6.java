package com.epam.kotlinkoans.i_introduction._6_Data_Classes;

import com.epam.kotlinkoans.util.JavaCode;

public class JavaCode6 extends JavaCode {

    public static class Person {
        private final String name;
        private final int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }
}
