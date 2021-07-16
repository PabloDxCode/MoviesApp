package com.pablogd.data.datasource.impl

import com.nhaarman.mockitokotlin2.whenever
import com.pablogd.data.common.mockedTvShow
import com.pablogd.data.database.MoviesDB
import com.pablogd.data.database.daos.TvShowDao
import com.pablogd.data.datasource.LocalTvShowDataSource
import com.pablogd.data.mappers.toTvShowEntity
import com.pablogd.domain.models.TvShow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LocalTvShowDataSourceTest {

    @Mock
    private lateinit var db: MoviesDB

    @Mock
    private lateinit var tvShowDao: TvShowDao

    private lateinit var localTvShowDataSource: LocalTvShowDataSource

    private lateinit var tvShows: List<TvShow>

    @Before
    fun setUp() {
        tvShows = listOf(mockedTvShow.copy(1))
        whenever(db.tvShowDao()).thenReturn(tvShowDao)
        localTvShowDataSource = LocalTvShowDataSourceImpl(db, Dispatchers.IO)
    }

    @Test
    fun `size return number of items in tv show db`() {
        runBlocking {
            whenever(tvShowDao.tvShowCount()).thenReturn(4)
            val result = localTvShowDataSource.size()
            Assert.assertEquals(4, result)
        }
    }

    @Test
    fun `saveTvShows should save tv shows and return state of item insert in db`() {
        runBlocking {
            whenever(tvShowDao.insertTvShows(tvShows.map { it.toTvShowEntity() })).thenReturn(arrayOf(1))
            val result = localTvShowDataSource.saveTvShows(tvShows)
            Assert.assertEquals(1, result.size)
        }
    }

    @Test
    fun `getTvShows should return tv shows from db`() {
        runBlocking {
            whenever(tvShowDao.getAll()).thenReturn(tvShows.map { it.toTvShowEntity() })
            val result = localTvShowDataSource.getTvShows()
            Assert.assertEquals(tvShows[0].name, result[0].name)
        }
    }

    @Test
    fun `findById should return tv show by id from db`() {
        runBlocking {
            whenever(tvShowDao.findById(1)).thenReturn(tvShows[0].toTvShowEntity())
            val result = localTvShowDataSource.findById(1)
            Assert.assertEquals(tvShows[0].name, result.name)
        }
    }

    @Test
    fun `clearMovies should delete all tv shows and return state of item deleted in db`() {
        runBlocking {
            whenever(tvShowDao.clearTvShows()).thenReturn(1)
            val result = localTvShowDataSource.clearTvShows()
            Assert.assertEquals(1, result)
        }
    }

}