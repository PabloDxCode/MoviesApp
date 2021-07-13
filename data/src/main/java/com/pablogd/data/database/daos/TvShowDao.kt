package com.pablogd.data.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pablogd.data.database.entities.TvShowEntity

@Dao
interface TvShowDao {

    @Query("SELECT * FROM tvShow")
    fun getAll(): List<TvShowEntity>

    @Query("SELECT * FROM tvShow WHERE id = :id")
    fun findById(id: Int): TvShowEntity

    @Query("SELECT COUNT(id) FROM tvShow")
    fun tvShowCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTvShows(movies: List<TvShowEntity>)

    @Query("DELETE FROM tvShow")
    fun clearTvShows()

}