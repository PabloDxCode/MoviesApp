package com.pablogd.data.server

import com.pablogd.data.models.response.MovieResponse
import com.pablogd.data.models.response.TvShowsResponse
import com.pablogd.data.models.response.VideosResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {

    @GET("movie/popular?language=es-MX&region=MX")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): MovieResponse

    @GET("movie/top_rated?language=es-MX")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): MovieResponse

    @GET("search/movie?language=es-MX&region=MX&page=1")
    suspend fun searchMovie(
        @Query("api_key") apiKey: String,
        @Query("query") query: String
    ): MovieResponse

    @GET("movie/{id}/videos?language=es-MX")
    suspend fun getMovieVideos(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): VideosResponse

    @GET("tv/popular?language=es-MX&region=MX")
    suspend fun getPopularTvShows(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): TvShowsResponse

    @GET("tv/top_rated?language=es-MX")
    suspend fun getTopRatedTvShows(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): TvShowsResponse

    @GET("search/tv?language=es-MX&region=MX&page=1")
    suspend fun searchTvShows(
        @Query("api_key") apiKey: String,
        @Query("query") query: String
    ): TvShowsResponse

    @GET("tv/{id}/videos?language=es-MX")
    suspend fun getTvShowVideos(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): VideosResponse

}