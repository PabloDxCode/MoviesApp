package com.pablogd.data.datasource

import com.pablogd.data.database.entities.DetailEntity

interface LocalDetailDataSource {

    suspend fun saveDetail(detail: DetailEntity): Long

    suspend fun getDetail(): DetailEntity

}