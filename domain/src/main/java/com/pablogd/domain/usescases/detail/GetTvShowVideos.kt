package com.pablogd.domain.usescases.detail

import com.pablogd.domain.Result
import com.pablogd.domain.models.Video
import com.pablogd.domain.repositories.DetailRepository

class GetTvShowVideos(private val detailRepository: DetailRepository) {

    suspend fun invoke(tvShowId: Int): Result<List<Video>> = detailRepository.getTvShowVideos(tvShowId)

}