package com.pablogd.data.mappers

import com.pablogd.data.database.entities.DetailEntity
import com.pablogd.data.database.entities.MovieEntity
import com.pablogd.data.models.MovieModel
import com.pablogd.data.models.VideoModel
import com.pablogd.domain.enums.SectionEnum
import com.pablogd.domain.models.Movie
import com.pablogd.domain.models.Video

fun MovieModel.toDomain(): Movie = Movie(
    0,
    this.id,
    this.title,
    this.overview,
    this.releaseDate,
    this.posterPath,
    this.backdropPath ?: "",
    this.originalLanguage,
    this.originalTitle,
    this.popularity,
    this.voteAverage,
    false
)

fun Movie.toMovieEntity(): MovieEntity = MovieEntity(
    this.movieId,
    this.title,
    this.overview,
    this.releaseDate,
    this.posterPath ?: "",
    this.backdropPath ?: "",
    this.originalLanguage,
    this.originalTitle,
    this.popularity,
    this.voteAverage,
    this.favorite
)

fun MovieEntity.toDomain(): Movie = Movie(
    this.getId().toInt(),
    this.movieId,
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

fun Movie.toDetailEntity(): DetailEntity = DetailEntity(
    1,
    this.movieId,
    this.title,
    this.overview,
    this.backdropPath ?: "",
    this.originalTitle,
    this.originalLanguage,
    this.releaseDate,
    this.popularity,
    this.voteAverage,
    SectionEnum.MOVIES.ordinal
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