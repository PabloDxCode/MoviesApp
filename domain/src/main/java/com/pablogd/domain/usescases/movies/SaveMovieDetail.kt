package com.pablogd.domain.usescases.movies

import com.pablogd.domain.Result
import com.pablogd.domain.models.Movie
import com.pablogd.domain.repositories.DetailRepository

class SaveMovieDetail(private val detailRepository: DetailRepository) {

    suspend fun invoke(movie: Movie): Result<Boolean> = detailRepository.saveDetail(movie)

}