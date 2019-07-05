package com.people.survey.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.people.survey.data.local.dao.MarvelDao;
import com.people.survey.data.local.entity.MarvelEntity;

@Database(entities = {MarvelEntity.class}, version = 1 , exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "weather";
    private static final Object LOCK = new Object();
    private static volatile AppDatabase sInstance;
    public static AppDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {
                    sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, AppDatabase.DATABASE_NAME).build();
                }
            }
        }
        return sInstance;
    }

    public abstract MarvelDao marvelDao();

}
