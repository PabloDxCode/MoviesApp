package com.pablogd.data.datasource.impl

import com.pablogd.data.database.MoviesDB
import com.pablogd.data.database.entities.DetailEntity
import com.pablogd.data.datasource.LocalDetailDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class LocalDetailDataSourceImpl(db: MoviesDB, private val dispatcher: CoroutineDispatcher): LocalDetailDataSource {

    private val detailDao = db.detailDao()

    override suspend fun saveDetail(detail: DetailEntity): Long = withContext(dispatcher) {
        detailDao.insertDetail(detail)
    }

    override suspend fun getDetail(): DetailEntity = withContext(dispatcher) {
        detailDao.getDetail()
    }

}