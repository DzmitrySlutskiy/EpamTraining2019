package com.epam.themes.backend;

import com.epam.themes.util.ICallback;

import java.util.List;

public interface IWebService<T> {

    public T create(final T t);

    public T read(final Long pId);

    void getEntities(final int pStartRange,
                     final int pEndRange,
                     IOperation<T> poperation);

    boolean update(final T t);

    void delete(final Long pId);

    void readAll(final ICallback<List<T>> pCallback);

    void readAll(final int pStartRange,
                 final int pEndRange,
                 final ICallback<List<T>> pCallback);
}
