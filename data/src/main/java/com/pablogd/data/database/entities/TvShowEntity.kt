package com.pablogd.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tvShow")
data class TvShowEntity(
    val tvShowId: Int,
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
){

    @field:PrimaryKey(autoGenerate = true)
    private var id: Long = 0

    fun getId() = id

    fun setId(id: Long){
        this.id = id
    }

}