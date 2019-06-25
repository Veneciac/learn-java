package com.gmail.veneciacalista.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.gmail.veneciacalista.dao.model.Movie;
import com.gmail.veneciacalista.helper.Constants;

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
