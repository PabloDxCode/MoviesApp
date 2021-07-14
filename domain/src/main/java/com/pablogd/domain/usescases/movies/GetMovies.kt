package com.pablogd.domain.usescases.movies

import com.pablogd.domain.Result
import com.pablogd.domain.models.Movie
import com.pablogd.domain.repositories.MoviesRepository

class GetMovies(private val moviesRepository: MoviesRepository) {

    suspend fun invoke(page: Int): Result<List<Movie>> = moviesRepository.getMovies(page)

}