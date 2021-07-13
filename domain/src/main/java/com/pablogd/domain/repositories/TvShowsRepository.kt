package com.pablogd.domain.repositories

import com.pablogd.domain.Result
import com.pablogd.domain.models.TvShow

interface TvShowsRepository {

    suspend fun getTvShows(page: Int): Result<List<TvShow>>

    suspend fun getPopularTvShows(page: Int): Result<List<TvShow>>

    suspend fun getTopRatedTvShows(page: Int): Result<List<TvShow>>

    suspend fun searchTvShows(query: String): Result<List<TvShow>>

}