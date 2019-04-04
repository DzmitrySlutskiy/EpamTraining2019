package com.epam.model;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class ListUsers {

    private List<User> usersList = new ArrayList<>();

    public ListUsers(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        for (int i = 0; i < jsonArray.length(); i++) {
            usersList.add(new User(jsonArray.getJSONObject(i)));
        }
    }

    public List<User> getUsersList() {
        return usersList;
    }
}
