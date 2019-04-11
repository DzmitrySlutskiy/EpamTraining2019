package com.epam.modelGson;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class UserGson {

    @SerializedName("_id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    @SerializedName("tags")
    private String[] tags;

    @SerializedName("registered")
    private Date registeredDate;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String[] getTags() {
        return tags;
    }
}
