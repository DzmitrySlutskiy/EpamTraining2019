package com.epam.themes.backend;

import com.epam.themes.backend.entities.Student;
import com.epam.themes.util.ICallback;

import java.util.List;

public interface IWebService<T> {

    void getEntities(final ICallback<List<T>> pCallback);

    void getEntities(final int pStartRange,
                     final int pEndRange,
                     IOperation<T> poperation);

    void removeEntity(final Long pId);
}
