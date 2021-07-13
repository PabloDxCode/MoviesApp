package com.pablogd.data.repositories

import com.pablogd.data.datasource.LocalTvShowDataSource
import com.pablogd.data.datasource.TvShowDataSource
import com.pablogd.domain.Result
import com.pablogd.domain.models.TvShow
import com.pablogd.domain.repositories.TvShowsRepository
import java.lang.Exception

class TvShowsRepositoryImpl(
    private val tvShowDataSource: TvShowDataSource,
    private val localTvShowDataSource: LocalTvShowDataSource
): TvShowsRepository {

    override suspend fun getTvShows(page: Int): Result<List<TvShow>> =
        try {
            if (localTvShowDataSource.isEmpty()) {
                val tvShows = tvShowDataSource.getPopularTvShows(page)
                localTvShowDataSource.saveTvShows(tvShows)
            }
            Result.Success(localTvShowDataSource.getTvShows())
        } catch (e: Exception) {
            Result.Error(e)
        }

    override suspend fun getPopularTvShows(page: Int): Result<List<TvShow>> =
        try {
            if(page == 1) localTvShowDataSource.clearTvShows()
            val movies = tvShowDataSource.getPopularTvShows(page)
            localTvShowDataSource.saveTvShows(movies)
            Result.Success(localTvShowDataSource.getTvShows())
        } catch (e: Exception) {
            Result.Error(e)
        }

    override suspend fun getTopRatedTvShows(page: Int): Result<List<TvShow>> =
        try {
            if(page == 1) localTvShowDataSource.clearTvShows()
            val movies = tvShowDataSource.getTopRatedTvShows(page)
            localTvShowDataSource.saveTvShows(movies)
            Result.Success(localTvShowDataSource.getTvShows())
        } catch (e: Exception) {
            Result.Error(e)
        }

    override suspend fun searchTvShows(query: String): Result<List<TvShow>> =
        try {
            Result.Success(tvShowDataSource.searchTvShows(query))
        } catch (e: Exception) {
            Result.Error(e)
        }

}