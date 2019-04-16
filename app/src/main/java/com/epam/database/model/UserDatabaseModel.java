package com.epam.database.model;

import com.epam.database.Constants;
import com.epam.database.Table;
import com.epam.database.fileds.dbLong;
import com.epam.database.fileds.dbPrimaryKey;
import com.epam.database.fileds.dbString;

@Table(name = Constants.DatabaseConstants.USER_TABLE_NAME)
public class UserDatabaseModel {

    @dbPrimaryKey
    @dbLong
    public static final String _ID = "_ID";

    @dbString
    public static final String NAME = "NAME";

    @dbString
    public static final String PASSWORD = "PASSWORD";
}
