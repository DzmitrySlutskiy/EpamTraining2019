package com.epam.database;

import android.content.ContentValues;
import android.database.Cursor;

public interface IDatabaseOperation {

    Cursor query(String pSql, String... pParams);

    long insert(final String pTableName, ContentValues pContentValues);

    long delete(final String pTableName, String pSql, String... pParams);

    //TODO write update
    //TODO write dbIndex logic
}
