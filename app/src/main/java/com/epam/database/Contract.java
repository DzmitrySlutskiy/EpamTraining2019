package com.epam.database;

import com.epam.database.model.Tag;
import com.epam.database.model.UserDatabaseModel;

import java.util.Arrays;
import java.util.List;

public final class Contract {

    public static List<Class<?>> getTables() {
        return Arrays.asList(UserDatabaseModel.class, Tag.class);
    }
}
