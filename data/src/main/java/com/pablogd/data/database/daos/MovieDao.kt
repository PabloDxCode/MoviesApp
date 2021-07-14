package com.pablogd.data.database.daos

import androidx.room.*
import com.pablogd.data.database.entities.MovieEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getAll(): List<MovieEntity>

    @Query("SELECT * FROM movie WHERE id = :id")
    fun findById(id: Int): MovieEntity

    @Query("SELECT COUNT(id) FROM movie")
    fun movieCount(): Int

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>): Array<Long>

    @Query("DELETE FROM movie")
    fun clearMovies(): Int

}