package com.pablogd.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity(
    val movieId: Int,
    val title: String,
    val overview: String,
    val releaseDate: String,
    val posterPath: String,
    val backdropPath: String,
    val originalLanguage: String,
    val originalTitle: String,
    val popularity: Double,
    val voteAverage: Double,
    val favorite: Boolean
){

    @field:PrimaryKey(autoGenerate = true)
    private var id: Long = 0

    fun getId() = id

    fun setId(id: Long){
        this.id = id
    }

}