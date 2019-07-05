package com.people.survey.data.local.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.people.survey.data.local.entity.MarvelEntity;

import java.util.Date;
import java.util.List;

@Dao // Required annotation for Dao to be recognized by Room
public interface MarvelDao {
    // Returns a list of all users in the database
    @Query("SELECT * FROM marvel")
    LiveData<List<MarvelEntity>> getAll();

    // Inserts multiple users
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<MarvelEntity> users);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(MarvelEntity users);

    // Deletes a single user
    @Delete
    void delete(MarvelEntity user);

    @Query("SELECT * FROM marvel WHERE createdby = :date")
    MarvelEntity getMarvelByDate(Date date);
}