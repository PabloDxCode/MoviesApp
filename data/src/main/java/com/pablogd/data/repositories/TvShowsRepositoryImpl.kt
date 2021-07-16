package com.pablogd.data.repositories

import com.pablogd.data.datasource.LocalTvShowDataSource
import com.pablogd.data.datasource.TvShowDataSource
import com.pablogd.domain.Result
import com.pablogd.domain.enums.ServiceErrorEnum
import com.pablogd.domain.models.TvShow
import com.pablogd.domain.repositories.TvShowsRepository
import java.lang.Exception

class TvShowsRepositoryImpl(
    private val tvShowDataSource: TvShowDataSource,
    private val localTvShowDataSource: LocalTvShowDataSource
): TvShowsRepository {

    override suspend fun getTvShows(page: Int): Result<List<TvShow>> =
        try {
            if (localTvShowDataSource.size() == 0) {
                val tvShows = tvShowDataSource.getPopularTvShows(page)
                localTvShowDataSource.saveTvShows(tvShows)
            }
            Result.Success(localTvShowDataSource.getTvShows())
        } catch (e: Exception) {
            Result.Error(e)
        }

    override suspend fun getPopularTvShows(page: Int): Result<List<TvShow>> =
        try {
            val newPage = if(page == 1) page else getNextPage()
            val movies = tvShowDataSource.getPopularTvShows(newPage)
            if(page == 1) localTvShowDataSource.clearTvShows()
            localTvShowDataSource.saveTvShows(movies)
            Result.Success(localTvShowDataSource.getTvShows())
        } catch (e: Exception) {
            Result.Error(getErrorMessage(page, e))
        }

    override suspend fun getTopRatedTvShows(page: Int): Result<List<TvShow>> =
        try {
            val newPage = if(page == 1) page else getNextPage()
            val movies = tvShowDataSource.getTopRatedTvShows(newPage)
            if(page == 1) localTvShowDataSource.clearTvShows()
            localTvShowDataSource.saveTvShows(movies)
            Result.Success(localTvShowDataSource.getTvShows())
        } catch (e: Exception) {
            Result.Error(getErrorMessage(page, e))
        }

    override suspend fun searchTvShows(query: String): Result<List<TvShow>> =
        try {
            Result.Success(tvShowDataSource.searchTvShows(query))
        } catch (e: Exception) {
            Result.Error(e)
        }

    private fun getErrorMessage(page: Int, e: Exception): Exception {
        val messageError = if(page == 1) ServiceErrorEnum.NEW_CATEGORY_SELECTED.name else e.message
        return Exception(messageError)
    }

    private suspend fun getNextPage(): Int {
        val newPage = localTvShowDataSource.size() / 20
        return newPage + 1
    }

}