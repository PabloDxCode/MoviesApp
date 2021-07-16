package com.pablogd.data.datasource

import com.pablogd.domain.models.Movie
import com.pablogd.domain.models.Video

interface MovieDataSource {

    @Throws
    suspend fun getPopularMovies(page: Int): List<Movie>

    @Throws
    suspend fun getTopRatedMovies(page: Int): List<Movie>

    @Throws
    suspend fun searchMovies(query: String): List<Movie>

    @Throws
    suspend fun getVideos(movieId: Int): List<Video>

}