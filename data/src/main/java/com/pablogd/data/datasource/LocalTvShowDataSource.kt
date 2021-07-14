package com.pablogd.data.datasource

import com.pablogd.domain.models.TvShow

interface LocalTvShowDataSource {

    suspend fun size(): Int

    suspend fun saveTvShows(tvShows: List<TvShow>): Array<Long>

    suspend fun getTvShows(): List<TvShow>

    suspend fun findById(id: Int): TvShow

    suspend fun clearTvShows(): Int

}