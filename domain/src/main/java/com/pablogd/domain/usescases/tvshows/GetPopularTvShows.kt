package com.pablogd.domain.usescases.tvshows

import com.pablogd.domain.Result
import com.pablogd.domain.models.TvShow
import com.pablogd.domain.repositories.TvShowsRepository

class GetPopularTvShows(private val tvShowsRepository: TvShowsRepository) {

    suspend fun invoke(page: Int): Result<List<TvShow>> = tvShowsRepository.getPopularTvShows(page)

}