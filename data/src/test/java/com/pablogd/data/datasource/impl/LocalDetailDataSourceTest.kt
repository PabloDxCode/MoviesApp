package com.pablogd.data.datasource.impl

import com.nhaarman.mockitokotlin2.whenever
import com.pablogd.data.common.mockedMovie
import com.pablogd.data.database.MoviesDB
import com.pablogd.data.database.daos.DetailDao
import com.pablogd.data.database.entities.DetailEntity
import com.pablogd.data.datasource.LocalDetailDataSource
import com.pablogd.data.mappers.toDetailEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LocalDetailDataSourceTest {

    @Mock
    private lateinit var db: MoviesDB

    @Mock
    private lateinit var detailDao: DetailDao

    private lateinit var localDetailDataSource: LocalDetailDataSource

    private lateinit var detailEntity: DetailEntity

    @Before
    fun setUp() {
        whenever(db.detailDao()).thenReturn(detailDao)
        detailEntity = mockedMovie.toDetailEntity()
        localDetailDataSource = LocalDetailDataSourceImpl(db, Dispatchers.IO)
    }

    @Test
    fun `saveDetail return one number when detail is saved`() {
        runBlocking {
            whenever(detailDao.insertDetail(detailEntity)).thenReturn(1)

            val result = localDetailDataSource.saveDetail(detailEntity)
            assertEquals(1, result)
        }
    }

    @Test
    fun `getDetail return detail movie or tv show`() {
        runBlocking {
            whenever(detailDao.getDetail()).thenReturn(detailEntity)
            val result = localDetailDataSource.getDetail()
            assertEquals(detailEntity, result)
        }
    }

}