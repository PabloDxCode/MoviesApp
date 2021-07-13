package com.pablogd.data.datasource

import com.pablogd.domain.models.TvShow
import com.pablogd.domain.models.Video

interface TvShowDataSource {

    suspend fun getPopularTvShows(page: Int): List<TvShow>

    suspend fun getTopRatedTvShows(page: Int): List<TvShow>

    suspend fun searchTvShows(query: String): List<TvShow>

    suspend fun getVideos(tvShowId: Int): List<Video>

}