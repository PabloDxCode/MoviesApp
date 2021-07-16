package com.pablogd.domain.models

import java.io.Serializable

data class Detail(
    val id: Int,
    val title: String,
    val detail: String,
    val backdropPath: String?,
    val originalTitle: String?,
    val originalLanguage: String,
    val releaseDate: String?,
    val popularity: Double,
    val voteAverage: Double,
    val section: Int
): Serializable
