package com.pablogd.domain.repositories

import com.pablogd.domain.Result
import com.pablogd.domain.models.Movie

interface MoviesRepository {

    suspend fun getMovies(page: Int): Result<List<Movie>>

    suspend fun getPopularMovies(page: Int): Result<List<Movie>>

    suspend fun getTopRatedMovies(page: Int): Result<List<Movie>>

    suspend fun searchMovies(query: String): Result<List<Movie>>

}