package com.epam.themes.backend;

public interface IOperation<T> extends Runnable {
    T doInBgh();

    void cancel();
}
