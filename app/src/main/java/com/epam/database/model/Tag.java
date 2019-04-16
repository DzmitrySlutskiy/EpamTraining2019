package com.epam.database.model;

import com.epam.database.Constants;
import com.epam.database.Table;
import com.epam.database.fileds.dbLong;
import com.epam.database.fileds.dbPrimaryKey;
import com.epam.database.fileds.dbString;

@Table(name = Constants.DatabaseConstants.TAG_TABLE_NAME)
public final class Tag {

    @dbLong
    @dbPrimaryKey
    public static final String _ID = "_id";

    @dbString
    public static final String VALUE = "VALUE";

    @dbLong
    public static final String USER_ID = "USER_ID";
}
