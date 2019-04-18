package com.andraganoid.playsomemedia.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {
        Video.class,
        Audio.class,
        Stream.class},
        version = 1)
public abstract class PlayDatabase extends RoomDatabase {

    private static PlayDatabase INSTANCE;

    public abstract VideoDao videoDao();

    public abstract AudioDao audioDao();

    public abstract StreamDao streamDao();

    public static PlayDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    PlayDatabase.class,
                    "play-database")
                    .build();
        }
        return INSTANCE;
    }
}
