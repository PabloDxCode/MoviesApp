package com.pablogd.domain.repositories

import com.pablogd.domain.Result
import com.pablogd.domain.models.Detail
import com.pablogd.domain.models.Movie
import com.pablogd.domain.models.TvShow

interface DetailRepository {

    suspend fun saveDetail(movie: Movie)

    suspend fun saveDetail(tvShow: TvShow)

    suspend fun getDetail(): Result<Detail>

}