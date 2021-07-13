package com.pablogd.data.mappers

import com.pablogd.data.database.entities.MovieEntity
import com.pablogd.data.models.MovieModel
import com.pablogd.data.models.VideoModel
import com.pablogd.domain.models.Movie
import com.pablogd.domain.models.Video

fun MovieModel.toDomain(): Movie = Movie(
    this.id,
    this.title,
    this.overview,
    this.releaseDate,
    this.posterPath,
    this.backdropPath?:"",
    this.originalLanguage,
    this.originalTitle,
    this.popularity,
    this.voteAverage,
    false
)

fun Movie.toEntity(): MovieEntity = MovieEntity(
    this.id,
    this.title,
    this.overview,
    this.releaseDate,
    this.posterPath?:"",
    this.backdropPath?:"",
    this.originalLanguage,
    this.originalTitle,
    this.popularity,
    this.voteAverage,
    this.favorite
)

fun MovieEntity.toDomain(): Movie = Movie(
    this.id,
    this.title,
    this.overview,
    this.releaseDate,
    this.posterPath,
    this.backdropPath,
    this.originalLanguage,
    this.originalTitle,
    this.popularity,
    this.voteAverage,
    this.favorite
)

fun VideoModel.toDomain(): Video = Video(
    this.id,
    this.iso31661,
    this.iso6391,
    this.key,
    this.name,
    this.site,
    this.size,
    this.type
)