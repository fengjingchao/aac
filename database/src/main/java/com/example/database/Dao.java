package com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

public class Dao {

    private MyDatabaseHelper mMyDatabaseHelper;
    private SQLiteDatabase db;
    private static Dao dao;
    private Context context;
    public static Dao getInstance(Context context, String databaseName, int databaseVersion){

        if (dao == null) {

            dao = new Dao(context, databaseName, databaseVersion);
        }
        return dao;
    }

    public Dao(Context context, String databaseName, int databaseVersion){
        mMyDatabaseHelper = new MyDatabaseHelper(context, databaseName, null, databaseVersion);
        db = mMyDatabaseHelper.getReadableDatabase();
        this.context =context;
    }
    public void insert(){
        ContentValues values = new ContentValues();

        values.put("name", "The Da Vinci Code");
        values.put("author", "Dan Brown");
        values.put("pages", 454);
        values.put("price", 16.96);
//向数据库插入数据
        db.insert("Book", null, values);

        values.clear();

        values.put("name", "The Lost Symbol");
        values.put("author", "Dan Brown");
        values.put("pages", 510);
        values.put("price", 19.96);

        db.insert("Book", null, values);
    }

    public void update(){
        ContentValues values = new ContentValues();

        values.put("price", 10.56);

//更新数据库的数据
        db.update("Book", values, "name=?", new String[]{"The Da Vinci Code"});
    }

    public void query() {
        Cursor cursor = db.query("Book", null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String author = cursor.getString(cursor.getColumnIndex("author"));
            int pages = cursor.getInt(cursor.getColumnIndex("pages"));
            double price = cursor.getDouble(cursor.getColumnIndex("price"));

            Log.d("Query BookStore.db", name);
            Log.d("Query BookStore.db", author);
            Log.d("Query BookStore.db", pages + "");
            Log.d("Query BookStore.db", price + "");
        }
        cursor.close();
    }

    public void delete(){
//删除数据库的数据
        db.delete("Book", "pages > ?", new String[]{"500"});
    }
    public void transaction(){

        //开启事务
        db.beginTransaction();

        try {

            Toast.makeText(context, "删除数据成功", Toast.LENGTH_SHORT).show();

        /*if (true){
            throw new NullPointerException();
        }*/

                    ContentValues values = new ContentValues();

            values.put("name", "Game of Thrones");
            values.put("author", "George Martin");
            values.put("pages", 720);
            values.put("price", 20.15);

            db.insert("Book", null, values);

            //事务已经执行成功
            db.setTransactionSuccessful();

            Toast.makeText(context, "插入数据成功", Toast.LENGTH_SHORT).show();

        } catch (NullPointerException e) {
            e.printStackTrace();
        } finally {
            //结束事务
            db.endTransaction();
        }
    }

}
