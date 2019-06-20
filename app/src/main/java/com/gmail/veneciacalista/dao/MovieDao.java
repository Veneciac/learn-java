package com.gmail.veneciacalista.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.gmail.veneciacalista.helper.Constants;
import com.gmail.veneciacalista.dao.model.Movie;

import java.util.List;

@Dao
public interface MovieDao {
    @Query("SELECT * FROM "+ Constants.TABLE_NAME_MOVIE)
    List<Movie> getAll();
    /*
     * Insert the object in database
     * @param note, object to be inserted
     */
    @Insert
    long insert(Movie note);

    /*
     * update the object in database
     * @param note, object to be updated
     */
    @Update
    void update(Movie repos);

    /*
     * delete the object from database
     * @param note, object to be deleted
     */
    @Delete
    void delete(Movie movie);

    /*
     * delete list of objects from database
     * @param note, array of objects to be deleted
     */
    @Delete
    void delete(Movie... movies);      // Note... is varargs, here note is an array


}
