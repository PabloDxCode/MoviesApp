package com.pablogd.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tvShow")
data class TvShowEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val name: String,
    val overview: String,
    val posterPath: String,
    val backdropPath: String,
    val firstAirDate: String,
    val originalLanguage: String,
    val originalName: String,
    val popularity: Double,
    val voteAverage: Double,
    val voteCount: Int
)