package com.pablogd.data.datasource.impl

import com.pablogd.data.datasource.TvShowDataSource
import com.pablogd.data.mappers.toDomain
import com.pablogd.data.server.MoviesApiConfig
import com.pablogd.domain.models.TvShow
import com.pablogd.domain.models.Video
import java.lang.Exception

class TvShowDataSourceImpl(
    private val apiKey: String, private val moviesApiConfig: MoviesApiConfig
) : TvShowDataSource {

    @Throws
    override suspend fun getPopularTvShows(page: Int): List<TvShow> =
        moviesApiConfig.service.getPopularTvShows(apiKey, page)
            .results
            .map { it.toDomain() }

    @Throws
    override suspend fun getTopRatedTvShows(page: Int): List<TvShow> =
        moviesApiConfig.service.getTopRatedTvShows(apiKey, page)
            .results
            .map { it.toDomain() }

    @Throws
    override suspend fun searchTvShows(query: String): List<TvShow> =
        moviesApiConfig.service.searchTvShows(apiKey, query)
            .results
            .map { it.toDomain() }

    @Throws
    override suspend fun getVideos(tvShowId: Int): List<Video> =
        moviesApiConfig.service.getMovieVideos(apiKey, tvShowId)
            .results
            .map { it.toDomain() }

}