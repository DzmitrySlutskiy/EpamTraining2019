package com.epam.themes.backend;

import com.epam.themes.util.ICallback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class CatsWebService implements IImageWebService {

    private final OkHttpClient mClient = new OkHttpClient();
    private final Executor mExecutor = Executors.newSingleThreadExecutor();

    @Override
    public void loadImages(final int pQuantity, final ICallback<List<String>> pResult) {
        mExecutor.execute(new Runnable() {

            @Override
            public void run() {
                try {
                    final Request request = new Request.Builder().url("https://api.thecatapi.com/v1/images/search?limit=" + pQuantity).build();

                    pResult.onResult(parseResponse(mClient.newCall(request).execute()));
                } catch (IOException pE) {
                    pResult.onResult(Collections.<String>emptyList());
                } catch (JSONException pE) {
                    pResult.onResult(Collections.<String>emptyList());
                }
            }
        });
    }

    private List<String> parseResponse(final Response pResponse) throws IOException, JSONException {
        final List<String> catsUris = new LinkedList<>();
        final JSONArray catsArray = new JSONArray(pResponse.body().string());

        for (int i = 0; i < catsArray.length(); i++) {
            catsUris.add(catsArray.getJSONObject(i).optString("url"));
        }

        return catsUris;
    }
}
