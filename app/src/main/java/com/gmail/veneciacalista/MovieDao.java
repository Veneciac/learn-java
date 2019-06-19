package com.gmail.veneciacalista;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface MovieDao {
    @Query("SELECT * From movie")
    List<Movie> getAll();
    /*
     * Insert the object in database
     * @param note, object to be inserted
     */
    @Insert
    void insert(Movie note);

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
    void delete(Movie note);

    /*
     * delete list of objects from database
     * @param note, array of objects to be deleted
     */
    @Delete
    void delete(Movie... note);      // Note... is varargs, here note is an array


}
