package com.pablogd.data.datasource.impl

import com.pablogd.data.database.MoviesDB
import com.pablogd.data.datasource.LocalTvShowDataSource
import com.pablogd.data.mappers.toDomain
import com.pablogd.data.mappers.toEntity
import com.pablogd.domain.models.TvShow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalTvShowDataSourceImpl(db: MoviesDB): LocalTvShowDataSource {

    private val tvShowDao = db.tvShowDao()

    override suspend fun isEmpty(): Boolean =
        withContext(Dispatchers.IO) { tvShowDao.tvShowCount() <= 0 }

    override suspend fun saveTvShows(tvShows: List<TvShow>) = withContext(Dispatchers.IO) {
        tvShowDao.insertTvShows(tvShows.map { it.toEntity() })
    }

    override suspend fun getTvShows(): List<TvShow> = withContext(Dispatchers.IO) {
        tvShowDao.getAll().map { it.toDomain() }
    }

    override suspend fun findById(id: Int): TvShow = withContext(Dispatchers.IO) {
        tvShowDao.findById(id).toDomain()
    }

    override suspend fun clearTvShows() = withContext(Dispatchers.IO) {
        tvShowDao.clearTvShows()
    }

}