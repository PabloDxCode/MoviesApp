package com.pablogd.domain.usescases.searchable

import com.pablogd.domain.Result
import com.pablogd.domain.models.TvShow
import com.pablogd.domain.repositories.TvShowsRepository

class SearchTvShows(private val tvShowsRepository: TvShowsRepository) {

    suspend fun invoke(query: String): Result<List<TvShow>> = tvShowsRepository.searchTvShows(query)

}