package com.pablogd.data.database.daos

import androidx.room.*
import com.pablogd.data.database.entities.TvShowEntity

@Dao
interface TvShowDao {

    @Query("SELECT * FROM tvShow")
    fun getAll(): List<TvShowEntity>

    @Query("SELECT * FROM tvShow WHERE id = :id")
    fun findById(id: Int): TvShowEntity

    @Query("SELECT COUNT(id) FROM tvShow")
    fun tvShowCount(): Int

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(movies: List<TvShowEntity>): Array<Long>

    @Query("DELETE FROM tvShow")
    fun clearTvShows(): Int

}