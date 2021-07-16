package com.pablogd.data.datasource

import com.pablogd.domain.models.Movie

interface LocalMovieDataSource {

    suspend fun size(): Int

    suspend fun saveMovies(movies: List<Movie>): Array<Long>

    suspend fun getMovies(): List<Movie>

    suspend fun findById(id: Int): Movie

    suspend fun clearMovies(): Int

}