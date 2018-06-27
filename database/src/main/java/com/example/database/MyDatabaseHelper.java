package com.example.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    //用于创建Book表
    public static final String CREATE_BOOK = "create table Book ("
            + "id integer primary key autoincrement, "
            + "author text, "
            + "price real, "
            + "pages integer, "
            + "name text, "
            + "catagory_id integer)";
    //用于创建Category表
    public static final String CREATE_CATEGORY = "create table Category ("
            + "id integer primary key autoincrement, "
            + "category_name text, "
            + "category_code integer)";
    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_BOOK);
        sqLiteDatabase.execSQL(CREATE_CATEGORY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        /*第一版的数据库只有Book表，第二版的数据库增加了Category表，
         * 为了保证用户体验，在不干扰前一版的数据的情况下，实现对数据
         * 库的平滑升级，简单的可以用此方法进行判断在升级*/
        switch (oldVersion){
            case 1:
                sqLiteDatabase.execSQL(CREATE_CATEGORY);
            case 2:
                sqLiteDatabase.execSQL("alter table Book add column category_id integer");
            default:
        }
    }
}
