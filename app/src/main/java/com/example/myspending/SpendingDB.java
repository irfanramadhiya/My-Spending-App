package com.example.myspending;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class SpendingDB {
    private DBHelper dbHelper;

    public SpendingDB(Context context){
        dbHelper = new DBHelper(context);
    }

    public void insertSpending(Spending spending){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.field_spending_name, spending.name);
        contentValues.put(DBHelper.field_spending_nominal, spending.nominal);
        contentValues.put(DBHelper.field_spending_date, spending.date);

        sqLiteDatabase.insert(DBHelper.table_spending, null, contentValues);

        sqLiteDatabase.close();
    }

    public void updateSpending(Spending spending, int id){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        String selection = "id = ?";
        String[] selectionArgs = {"" + id};
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.field_spending_name, spending.name);
        contentValues.put(DBHelper.field_spending_nominal, spending.nominal);

        sqLiteDatabase.update(DBHelper.table_spending, contentValues, selection, selectionArgs);

        sqLiteDatabase.close();
    }

    public Spending getSpending(int id){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();

        String selection = "id = ?";
        String[] selectionArgs = {"" + id};

        Cursor cursor = sqLiteDatabase.query(DBHelper.table_spending, null, selection, selectionArgs, null, null, null);

        Spending spending = null;

        if (cursor.moveToFirst()){
            spending = new Spending();
            spending.id = cursor.getInt(cursor.getColumnIndex(DBHelper.field_spending_id));
            spending.name = cursor.getString(cursor.getColumnIndex(DBHelper.field_spending_name));
            spending.nominal = cursor.getInt(cursor.getColumnIndex(DBHelper.field_spending_nominal));
            spending.date = cursor.getString(cursor.getColumnIndex(DBHelper.field_spending_date));
        }

        cursor.close();

        return spending;
    }



    public ArrayList<Spending> getAll(){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(DBHelper.table_spending, null, null, null, null, null, null);


        ArrayList<Spending> spendings = new ArrayList<Spending>();
        Spending spending = null;

        while (cursor.moveToNext()){
            spending = new Spending();
            spending.id = cursor.getInt(cursor.getColumnIndex(DBHelper.field_spending_id));
            spending.name = cursor.getString(cursor.getColumnIndex(DBHelper.field_spending_name));
            spending.nominal = cursor.getInt(cursor.getColumnIndex(DBHelper.field_spending_nominal));
            spending.date = cursor.getString(cursor.getColumnIndex(DBHelper.field_spending_date));
            spendings.add(spending);
        }

        cursor.close();

        return spendings;
    }
}
