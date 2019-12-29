package com.example.retroft_recycleview_easy_demo.Model.Room;

//import androidx.arch.persistence.room.Database;
import androidx.room.Database;
import androidx.room.RoomDatabase;

//import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Task.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();
}