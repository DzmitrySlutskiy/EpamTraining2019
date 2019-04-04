package com.epam.model;

import org.json.JSONException;
import org.json.JSONObject;

public class User {

    private String id;

    private String name;

    private String company;

    private String email;

    private Tag tag;

    User(JSONObject jsonObject) throws JSONException {
        name = jsonObject.getString("name");
        company = jsonObject.getString("company");
        email = jsonObject.getString("email");
        id = jsonObject.getString("_id");
        tag = new Tag(jsonObject.getJSONArray("tags"));

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public String getEmail() {
        return email;
    }

    public Tag getTag() {
        return tag;
    }
}
