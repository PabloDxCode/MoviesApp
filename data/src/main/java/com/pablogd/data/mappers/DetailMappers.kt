package com.pablogd.data.mappers

import com.pablogd.data.database.entities.DetailEntity
import com.pablogd.domain.models.Detail

fun DetailEntity.toDomain(): Detail = Detail(
    this.movieTvShowId,
    this.title,
    this.detail,
    this.backdropPath,
    this.originalTitle,
    this.originalLanguage,
    this.releaseDate,
    this.popularity,
    this.voteAverage,
    this.section
)