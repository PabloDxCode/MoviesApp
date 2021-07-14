package com.pablogd.data.repositories

import com.pablogd.data.datasource.LocalDetailDataSource
import com.pablogd.data.mappers.toDetailEntity
import com.pablogd.data.mappers.toDomain
import com.pablogd.domain.Result
import com.pablogd.domain.models.Detail
import com.pablogd.domain.models.Movie
import com.pablogd.domain.models.TvShow
import com.pablogd.domain.repositories.DetailRepository

class DetailRepositoryImpl(
    private val localDetailDataSource: LocalDetailDataSource
) : DetailRepository {

    override suspend fun saveDetail(movie: Movie) {
        localDetailDataSource.saveDetail(movie.toDetailEntity())
    }

    override suspend fun saveDetail(tvShow: TvShow) {
        localDetailDataSource.saveDetail(tvShow.toDetailEntity())
    }

    override suspend fun getDetail(): Result<Detail> =
        Result.Success(localDetailDataSource.getDetail().toDomain())

}