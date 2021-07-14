package com.pablogd.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "detail")
data class DetailEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val movieTvShowId: Int,
    val title: String,
    val detail: String,
    val backdropPath: String,
    val originalTitle: String,
    val originalLanguage: String,
    val releaseDate: String?,
    val popularity: Double,
    val voteAverage: Double
)