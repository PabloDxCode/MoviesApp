package com.pablogd.domain.usescases.searchable

import com.pablogd.domain.Result
import com.pablogd.domain.models.Movie
import com.pablogd.domain.repositories.MoviesRepository

class SearchMovies(private val moviesRepository: MoviesRepository) {

    suspend fun invoke(query: String): Result<List<Movie>> = moviesRepository.searchMovies(query)

}