package com.example.room.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.database.Cursor;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAllUsers();

    @Query("SELECT * FROM user WHERE name=:name")
    User getUser(String name);

    @Query("SELECT * FROM user")
    Cursor getUserCursor();

    @Query("SELECT * FROM user WHERE age=:age")
    List<User> getUsersByAge(int age);

    @Query("SELECT * FROM user WHERE age=:age LIMIT :max")
    List<User> getUsersByAge(int max, int... age);

    @Insert
    void insert(User... users);

    @Insert
    void insert(List<User> userLists);

    @Update
    void update(User... users);

    @Update
    void update(List<User> userLists);

    @Delete
    void delete(User... users);

    @Delete
    void delete(List<User> userLists);

}
