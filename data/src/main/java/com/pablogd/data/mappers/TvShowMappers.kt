package com.pablogd.data.mappers

import com.pablogd.data.database.entities.TvShowEntity
import com.pablogd.data.models.TvShowModel
import com.pablogd.domain.models.TvShow

fun TvShowModel.toDomain(): TvShow = TvShow(
    this.id,
    this.name,
    this.overview,
    this.posterPath,
    this.backdropPath,
    this.firstAirDate,
    this.originalLanguage,
    this.originalName,
    this.popularity,
    this.voteAverage,
    this.voteCount
)

fun TvShow.toEntity(): TvShowEntity = TvShowEntity(
    this.id,
    this.name,
    this.overview,
    this.posterPath,
    this.backdropPath,
    this.firstAirDate,
    this.originalLanguage,
    this.originalName,
    this.popularity,
    this.voteAverage,
    this.voteCount
)

fun TvShowEntity.toDomain(): TvShow = TvShow(
    this.id,
    this.name,
    this.overview,
    this.posterPath,
    this.backdropPath,
    this.firstAirDate,
    this.originalLanguage,
    this.originalName,
    this.popularity,
    this.voteAverage,
    this.voteCount
)