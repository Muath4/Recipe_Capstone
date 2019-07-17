package com.example.android.test.database;


import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

@androidx.room.Database(entities = Entity.class,version = 1,exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {

    public static MyDatabase database;

    public abstract EntityDao moviesDao();

    public static synchronized MyDatabase getDatabase(Context context) {
        if (database == null)
            database = Room.databaseBuilder(context.getApplicationContext(), MyDatabase.class, "entity_database")
                    .fallbackToDestructiveMigration()
                    .build();
        return database;
    }
}
