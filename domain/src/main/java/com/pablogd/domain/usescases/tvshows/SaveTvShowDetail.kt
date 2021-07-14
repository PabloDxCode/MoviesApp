package com.pablogd.domain.usescases.tvshows

import com.pablogd.domain.models.TvShow
import com.pablogd.domain.repositories.DetailRepository

class SaveTvShowDetail(private val detailRepository: DetailRepository) {

    suspend fun invoke(tvShow: TvShow) = detailRepository.saveDetail(tvShow)

}