package com.example.myspending;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final String db_name = "SpendingDB";
    private static final int db_version = 1;

    public static final String table_spending = "Spending";
    public static final String field_spending_id = "id";
    public static final String field_spending_name = "name";
    public static final String field_spending_nominal = "nominal";
    public static final String field_spending_date = "date";

    private static final String create_spending = "CREATE TABLE IF NOT EXISTS " + table_spending + " (" +
            field_spending_id + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            field_spending_name + " TEXT," +
            field_spending_nominal + " INTEGER," +
            field_spending_date + " TEXT)";

    public DBHelper(@Nullable Context context) {
        super(context, db_name, null, db_version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(create_spending);

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
