package com.pablogd.data.datasource.impl

import com.pablogd.data.database.MoviesDB
import com.pablogd.data.datasource.LocalMovieDataSource
import com.pablogd.data.mappers.toDomain
import com.pablogd.data.mappers.toMovieEntity
import com.pablogd.domain.models.Movie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class LocalMovieDataSourceImpl(db: MoviesDB, private val dispatcher: CoroutineDispatcher) : LocalMovieDataSource {

    private val movieDao = db.movieDao()

    override suspend fun size(): Int = withContext(dispatcher) { movieDao.movieCount() }

    override suspend fun saveMovies(movies: List<Movie>) = withContext(dispatcher) {
        movieDao.insertMovies(movies.map { it.toMovieEntity() })
    }

    override suspend fun getMovies(): List<Movie> = withContext(dispatcher) {
        movieDao.getAll().map { it.toDomain() }
    }

    override suspend fun findById(id: Int): Movie = withContext(dispatcher) {
        movieDao.findById(id).toDomain()
    }

    override suspend fun clearMovies() = withContext(dispatcher) {
        movieDao.clearMovies()
    }

}