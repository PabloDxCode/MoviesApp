package com.pablogd.data.datasource.impl

import com.pablogd.data.database.MoviesDB
import com.pablogd.data.datasource.LocalMovieDataSource
import com.pablogd.data.mappers.toDomain
import com.pablogd.data.mappers.toEntity
import com.pablogd.domain.models.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalMovieDataSourceImpl(db: MoviesDB) : LocalMovieDataSource {

    private val movieDao = db.movieDao()

    override suspend fun isEmpty(): Boolean =
        withContext(Dispatchers.IO) { movieDao.movieCount() <= 0 }

    override suspend fun saveMovies(movies: List<Movie>) = withContext(Dispatchers.IO) {
        movieDao.insertMovies(movies.map { it.toEntity() })
    }

    override suspend fun getMovies(): List<Movie> = withContext(Dispatchers.IO) {
        movieDao.getAll().map { it.toDomain() }
    }

    override suspend fun findById(id: Int): Movie = withContext(Dispatchers.IO) {
        movieDao.findById(id).toDomain()
    }

    override suspend fun clearMovies() = withContext(Dispatchers.IO) {
        movieDao.clearMovies()
    }

}