package com.gmail.veneciacalista.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.gmail.veneciacalista.helper.Constants;
import com.gmail.veneciacalista.dao.model.Movie;

@Database(entities = { Movie.class }, version = 1, exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {
    public abstract MovieDao getMovieDao();

    private static MovieDatabase movieDB;

    public static MovieDatabase getInstance(Context context) {
        if (null == movieDB) {
            movieDB = buildDatabaseInstance(context);
        }
        return movieDB;
    }

    private static MovieDatabase buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context,
                MovieDatabase.class,
                Constants.DB_NAME).allowMainThreadQueries().build();
    }

    public  void cleanUp(){
        movieDB = null;
    }
}
