package com.pablogd.data.datasource.impl

import com.pablogd.data.datasource.MovieDataSource
import com.pablogd.data.mappers.toDomain
import com.pablogd.data.server.MoviesApiConfig
import com.pablogd.domain.models.Movie
import com.pablogd.domain.models.Video

class MovieDataSourceImpl(
    private val apiKey: String, private val moviesApiConfig: MoviesApiConfig
) : MovieDataSource {

    @Throws
    override suspend fun getPopularMovies(page: Int): List<Movie> =
        moviesApiConfig.service.getPopularMovies(apiKey, page)
            .results
            .map { it.toDomain() }

    override suspend fun getTopRatedMovies(page: Int): List<Movie> =
        moviesApiConfig.service.getTopRatedMovies(apiKey, page)
            .results
            .map { it.toDomain() }

    override suspend fun searchMovies(query: String): List<Movie> =
        moviesApiConfig.service.searchMovie(apiKey, query)
            .results
            .map { it.toDomain() }

    override suspend fun getVideos(movieId: Int): List<Video> =
        moviesApiConfig.service.getMovieVideos(apiKey, movieId)
            .results
            .map { it.toDomain() }

}