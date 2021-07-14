package com.pablogd.domain.usescases.detail

import com.pablogd.domain.Result
import com.pablogd.domain.models.Video
import com.pablogd.domain.repositories.DetailRepository

class GetMovieVideos(private val detailRepository: DetailRepository) {

    suspend fun invoke(movieId: Int): Result<List<Video>> = detailRepository.getMovieVideos(movieId)

}