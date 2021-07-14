package com.pablogd.data.datasource.impl

import com.pablogd.data.database.MoviesDB
import com.pablogd.data.datasource.LocalTvShowDataSource
import com.pablogd.data.mappers.toDomain
import com.pablogd.data.mappers.toTvShowEntity
import com.pablogd.domain.models.TvShow
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class LocalTvShowDataSourceImpl(db: MoviesDB, private val dispatcher: CoroutineDispatcher) : LocalTvShowDataSource {

    private val tvShowDao = db.tvShowDao()

    override suspend fun size(): Int = withContext(dispatcher) { tvShowDao.tvShowCount() }

    override suspend fun saveTvShows(tvShows: List<TvShow>) = withContext(dispatcher) {
        tvShowDao.insertTvShows(tvShows.map { it.toTvShowEntity() })
    }

    override suspend fun getTvShows(): List<TvShow> = withContext(dispatcher) {
        tvShowDao.getAll().map { it.toDomain() }
    }

    override suspend fun findById(id: Int): TvShow = withContext(dispatcher) {
        tvShowDao.findById(id).toDomain()
    }

    override suspend fun clearTvShows() = withContext(dispatcher) {
        tvShowDao.clearTvShows()
    }

}