package com.pablogd.domain.repositories

import com.pablogd.domain.Result
import com.pablogd.domain.models.Detail
import com.pablogd.domain.models.Movie
import com.pablogd.domain.models.TvShow
import com.pablogd.domain.models.Video

interface DetailRepository {

    suspend fun saveDetail(movie: Movie): Result<Boolean>

    suspend fun saveDetail(tvShow: TvShow): Result<Boolean>

    suspend fun getDetail(): Result<Detail>

    suspend fun getMovieVideos(movieId: Int): Result<List<Video>>

    suspend fun getTvShowVideos(tvShowId: Int): Result<List<Video>>

}