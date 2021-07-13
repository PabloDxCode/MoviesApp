package com.pablogd.data.repositories

import com.pablogd.data.datasource.LocalMovieDataSource
import com.pablogd.data.datasource.MovieDataSource
import com.pablogd.domain.Result
import com.pablogd.domain.models.Movie
import com.pablogd.domain.repositories.MoviesRepository
import java.lang.Exception

class MoviesRepositoryImpl(
    private val movieDataSource: MovieDataSource,
    private val localMovieDataSource: LocalMovieDataSource
) : MoviesRepository {

    override suspend fun getMovies(page: Int): Result<List<Movie>> =
        try {
            if (localMovieDataSource.isEmpty()) {
                val movies = movieDataSource.getPopularMovies(page)
                localMovieDataSource.saveMovies(movies)
            }
            Result.Success(localMovieDataSource.getMovies())
        } catch (e: Exception) {
            Result.Error(e)
        }

    override suspend fun getPopularMovies(page: Int): Result<List<Movie>> =
        try {
            if(page == 1) localMovieDataSource.clearMovies()
            val movies = movieDataSource.getPopularMovies(page)
            localMovieDataSource.saveMovies(movies)
            Result.Success(localMovieDataSource.getMovies())
        } catch (e: Exception) {
            Result.Error(e)
        }

    override suspend fun getTopRatedMovies(page: Int): Result<List<Movie>> =
        try {
            if(page == 1) localMovieDataSource.clearMovies()
            val movies = movieDataSource.getTopRatedMovies(page)
            localMovieDataSource.saveMovies(movies)
            Result.Success(localMovieDataSource.getMovies())
        } catch (e: Exception) {
            Result.Error(e)
        }

    override suspend fun searchMovies(query: String): Result<List<Movie>> =
        try {
            Result.Success(movieDataSource.searchMovies(query))
        } catch (e: Exception) {
            Result.Error(e)
        }

}