package com.epam.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import com.epam.database.fileds.dbBoolean;
import com.epam.database.fileds.dbDouble;
import com.epam.database.fileds.dbLong;
import com.epam.database.fileds.dbPrimaryKey;
import com.epam.database.fileds.dbString;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class DatabaseHelper extends SQLiteOpenHelper implements IDatabaseOperation {

    private static final String SQL_TABLE_CREATE_TEMPLATE = "CREATE TABLE IF NOT EXISTS %s (%s);";
    private static final String SQL_TABLE_CREATE_FIELD_TEMPLATE = "%s %s";

    public DatabaseHelper(@Nullable final Context context, @Nullable final SQLiteDatabase.CursorFactory factory, final int version) {
        super(context, Constants.DatabaseConstants.DATABASE_NAME, factory, version);
    }

    public DatabaseHelper(@Nullable final Context context, @Nullable final SQLiteDatabase.CursorFactory factory, final int version, @Nullable final DatabaseErrorHandler errorHandler) {
        super(context, Constants.DatabaseConstants.DATABASE_NAME, factory, version, errorHandler);
    }


    private String getCreateTableString(Class<?> pClass) {
        final String tableName = getTableName(pClass);

        if (tableName != null) {
            final Field[] fields = pClass.getFields();

            final StringBuilder builder = new StringBuilder();

            for (int i = 0; i < fields.length; i++) {
                final Field field = fields[i];
                String type = "";

                final dbPrimaryKey primaryKeyAnnotation = field.getAnnotation(dbPrimaryKey.class);

                final Annotation[] annotations = field.getAnnotations();

                for (final Annotation typeAnnotation : annotations) {
                    if (typeAnnotation instanceof dbString) {
                        type = ((dbString) typeAnnotation).name();
                    } else if (typeAnnotation instanceof dbLong) {
                        type = ((dbLong) typeAnnotation).name();
                    } else if (typeAnnotation instanceof dbBoolean) {
                        type = ((dbBoolean) typeAnnotation).name();
                    } else if (typeAnnotation instanceof dbDouble) {
                        type = ((dbDouble) typeAnnotation).name();
                    } else if (typeAnnotation instanceof dbPrimaryKey) {
                    } else {
                        throw new IllegalStateException("Field don't have type annotation");
                    }
                }


                final String fieldName = field.getName();

                String primaryKey = "";

                if (primaryKeyAnnotation != null) {
                    primaryKey = Constants.DatabaseConstants.PRIMARY_KEY;
                }

                final String template = fieldName + " " + type + " " + primaryKey;

                builder.append(template);

                if (i != fields.length - 1) {
                    builder.append(",");
                }
            }

            return String.format(SQL_TABLE_CREATE_TEMPLATE, tableName, builder.toString());
        } else {
            return "";
        }
    }

    private String getTableName(final Class<?> pClass) {
        final Table annotation = pClass.getAnnotation(Table.class);

        if (annotation != null) {
            return annotation.name();
        }

        return null;
    }

    @Override
    public void onCreate(final SQLiteDatabase db) {
        for (final Class<?> table : Contract.getTables()) {

            final String createTableString = getCreateTableString(table);

            Log.d("Magic", createTableString);

            if (!createTableString.equals("")) {
                db.execSQL(createTableString);
            }
        }
    }

    @Override
    public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
        throw new UnsupportedOperationException("Upgrade not supported");
    }

    @Override
    public Cursor query(final String pSql, final String... pParams) {
        final SQLiteDatabase readableDatabase = getReadableDatabase();

        readableDatabase.beginTransaction();

        final Cursor cursor;

        try {
            cursor = readableDatabase.rawQuery(pSql, pParams);
            readableDatabase.setTransactionSuccessful();
        } finally {
            readableDatabase.endTransaction();
        }

        return cursor;
    }

    @Override
    public long insert(final String pTableName, final ContentValues pContentValues) {
        final SQLiteDatabase writableDatabase = getWritableDatabase();

        writableDatabase.beginTransaction();

        final long insert;

        try {
            insert = writableDatabase.insertWithOnConflict(pTableName, null, pContentValues, SQLiteDatabase.CONFLICT_REPLACE);
            writableDatabase.setTransactionSuccessful();
        } finally {
            writableDatabase.endTransaction();
        }

        return insert;
    }

    @Override
    public long delete(final String pTableName, final String pSql, final String... pParams) {
        final SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.beginTransaction();

        final int delete;

        try {
            delete = writableDatabase.delete(pTableName, pSql, pParams);
            writableDatabase.setTransactionSuccessful();
        } finally {
            writableDatabase.endTransaction();
        }

        return delete;
    }
}
