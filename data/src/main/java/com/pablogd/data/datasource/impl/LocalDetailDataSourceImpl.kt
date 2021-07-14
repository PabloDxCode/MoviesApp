package com.pablogd.data.datasource.impl

import com.pablogd.data.database.MoviesDB
import com.pablogd.data.database.entities.DetailEntity
import com.pablogd.data.datasource.LocalDetailDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalDetailDataSourceImpl(db: MoviesDB): LocalDetailDataSource {

    private val detailDao = db.detailDao()

    override suspend fun saveDetail(detail: DetailEntity) = withContext(Dispatchers.IO) {
        detailDao.insertDetail(detail)
    }

    override suspend fun getDetail(): DetailEntity = withContext(Dispatchers.IO) {
        detailDao.getDetail()
    }

}