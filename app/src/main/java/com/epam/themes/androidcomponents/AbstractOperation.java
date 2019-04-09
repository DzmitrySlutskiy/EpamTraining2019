package com.epam.themes.androidcomponents;

import com.epam.themes.backend.IOperation;
import com.epam.themes.util.ICallback;

public abstract class AbstractOperation<T> implements IOperation<T> {
    private ICallback<T> callback;
    private boolean canceled = false;

    public AbstractOperation(ICallback<T> callback) {

        this.callback = callback;
    }

    @Override
    public void run() {
        try {
            T t = doInBgh();
            callback.onResult(t);
        } catch (Exception e) {
            callback.onError(e);
        }
    }

    @Override
    public void cancel() {

    }
}
