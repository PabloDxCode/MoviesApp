package com.pablogd.data.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pablogd.data.database.entities.MovieEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getAll(): List<MovieEntity>

    @Query("SELECT * FROM movie WHERE id = :id")
    fun findById(id: Int): MovieEntity

    @Query("SELECT COUNT(id) FROM movie")
    fun movieCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMovies(movies: List<MovieEntity>)

    @Query("DELETE FROM movie")
    fun clearMovies()

}