package com.epam.model;

import org.json.JSONArray;
import org.json.JSONException;

public class Tag {

    private String[] values;

    Tag(JSONArray json) throws JSONException {
        values = new String[json.length()];
        for (int i = 0; i < json.length(); i++) {
            values[i] = json.getString(i);
        }
    }

    public String[] getValues() {
        return values;
    }
}
