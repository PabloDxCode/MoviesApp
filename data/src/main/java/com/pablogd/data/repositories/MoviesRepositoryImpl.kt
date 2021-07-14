package com.pablogd.data.repositories

import com.pablogd.data.datasource.LocalMovieDataSource
import com.pablogd.data.datasource.MovieDataSource
import com.pablogd.domain.Result
import com.pablogd.domain.enums.ServiceErrorEnum
import com.pablogd.domain.models.Movie
import com.pablogd.domain.repositories.MoviesRepository
import java.lang.Exception

class MoviesRepositoryImpl(
    private val movieDataSource: MovieDataSource,
    private val localMovieDataSource: LocalMovieDataSource
) : MoviesRepository {

    override suspend fun getMovies(page: Int): Result<List<Movie>> =
        try {
            if (localMovieDataSource.size() == 0) {
                val movies = movieDataSource.getPopularMovies(page)
                localMovieDataSource.saveMovies(movies)
            }
            Result.Success(localMovieDataSource.getMovies())
        } catch (e: Exception) {
            Result.Error(e)
        }

    override suspend fun getPopularMovies(page: Int): Result<List<Movie>> =
        try {
            val newPage = if(page == 1) page else getNextPage()
            val movies = movieDataSource.getPopularMovies(newPage)
            if(page == 1) localMovieDataSource.clearMovies()
            localMovieDataSource.saveMovies(movies)
            Result.Success(localMovieDataSource.getMovies())
        } catch (e: Exception) {
            Result.Error(getErrorMessage(page, e))
        }

    override suspend fun getTopRatedMovies(page: Int): Result<List<Movie>> =
        try {
            val newPage = if(page == 1) page else getNextPage()
            val movies = movieDataSource.getTopRatedMovies(newPage)
            if(page == 1) localMovieDataSource.clearMovies()
            localMovieDataSource.saveMovies(movies)
            Result.Success(localMovieDataSource.getMovies())
        } catch (e: Exception) {
            Result.Error(getErrorMessage(page, e))
        }

    override suspend fun searchMovies(query: String): Result<List<Movie>> =
        try {
            Result.Success(movieDataSource.searchMovies(query))
        } catch (e: Exception) {
            Result.Error(e)
        }

    private fun getErrorMessage(page: Int, e: Exception): Exception {
        val messageError = if(page == 1) ServiceErrorEnum.NEW_CATEGORY_SELECTED.name else e.message
        return Exception(messageError)
    }

    private suspend fun getNextPage(): Int {
        val newPage = localMovieDataSource.size() / 20
        return newPage + 1
    }

}