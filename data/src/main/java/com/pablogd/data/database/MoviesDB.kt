package com.pablogd.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pablogd.data.database.daos.DetailDao
import com.pablogd.data.database.daos.MovieDao
import com.pablogd.data.database.daos.TvShowDao
import com.pablogd.data.database.entities.DetailEntity
import com.pablogd.data.database.entities.MovieEntity
import com.pablogd.data.database.entities.TvShowEntity

@Database(entities = [MovieEntity::class, TvShowEntity::class, DetailEntity::class], version = 1, exportSchema = false)
abstract class MoviesDB : RoomDatabase() {

    companion object {
        fun build(context: Context, dbName: String) = Room.databaseBuilder(
            context,
            MoviesDB::class.java,
            dbName
        ).build()
    }

    abstract fun movieDao(): MovieDao

    abstract fun tvShowDao(): TvShowDao

    abstract fun detailDao(): DetailDao

}