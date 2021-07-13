package com.pablogd.data.datasource

import com.pablogd.domain.models.TvShow

interface LocalTvShowDataSource {

    suspend fun isEmpty(): Boolean

    suspend fun saveTvShows(tvShows: List<TvShow>)

    suspend fun getTvShows(): List<TvShow>

    suspend fun findById(id: Int): TvShow

    suspend fun clearTvShows()

}