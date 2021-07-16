package com.pablogd.data.datasource

import com.pablogd.domain.models.TvShow
import com.pablogd.domain.models.Video

interface TvShowDataSource {

    @Throws
    suspend fun getPopularTvShows(page: Int): List<TvShow>

    @Throws
    suspend fun getTopRatedTvShows(page: Int): List<TvShow>

    @Throws
    suspend fun searchTvShows(query: String): List<TvShow>

    @Throws
    suspend fun getVideos(tvShowId: Int): List<Video>

}