package com.pablogd.domain.models

data class TvShow(
    val id: Int,
    val name: String,
    val overview: String,
    val posterPath: String,
    val backdropPath: String,
    val firstAirDate: String,
    val genreIds: List<Int>,
    val originCountry: List<String>,
    val originalLanguage: String,
    val originalName: String,
    val popularity: Double,
    val voteAverage: Double,
    val voteCount: Int
)