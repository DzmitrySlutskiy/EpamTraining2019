package com.epam.themes.backend;

import com.epam.themes.backend.entities.Student;
import com.epam.themes.util.ICallback;

import java.util.List;

public interface IWebService<T> {

    void getEntities(final ICallback<List<T>> pCallback);

    void getEntities(final int pStartRange,
                     final int pEndRange,
                     final ICallback<List<T>> pCallback);

    void removeEntity(final int pId);

    void addEntity(final Student student);

    void editEntity(int position, String name, int counter);
}
