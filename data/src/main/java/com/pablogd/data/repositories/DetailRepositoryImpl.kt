package com.pablogd.data.repositories

import com.pablogd.data.datasource.LocalDetailDataSource
import com.pablogd.data.datasource.MovieDataSource
import com.pablogd.data.datasource.TvShowDataSource
import com.pablogd.data.mappers.toDetailEntity
import com.pablogd.data.mappers.toDomain
import com.pablogd.domain.Result
import com.pablogd.domain.models.Detail
import com.pablogd.domain.models.Movie
import com.pablogd.domain.models.TvShow
import com.pablogd.domain.models.Video
import com.pablogd.domain.repositories.DetailRepository
import java.lang.Exception

class DetailRepositoryImpl(
    private val localDetailDataSource: LocalDetailDataSource,
    private val getMovieDataSource: MovieDataSource,
    private val getTvShowDataSource: TvShowDataSource
) : DetailRepository {

    override suspend fun saveDetail(movie: Movie) {
        localDetailDataSource.saveDetail(movie.toDetailEntity())
    }

    override suspend fun saveDetail(tvShow: TvShow) {
        localDetailDataSource.saveDetail(tvShow.toDetailEntity())
    }

    override suspend fun getDetail(): Result<Detail> =
        Result.Success(localDetailDataSource.getDetail().toDomain())

    override suspend fun getMovieVideos(movieId: Int): Result<List<Video>> = try {
        val videos = getMovieDataSource.getVideos(movieId)
        Result.Success(videos)
    } catch (e: Exception) {
        Result.Error(e)
    }

    override suspend fun getTvShowVideos(tvShowId: Int): Result<List<Video>> = try {
        val videos = getTvShowDataSource.getVideos(tvShowId)
        Result.Success(videos)
    } catch (e: Exception) {
        Result.Error(e)
    }

}