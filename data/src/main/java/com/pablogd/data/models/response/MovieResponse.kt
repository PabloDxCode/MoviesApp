package com.pablogd.data.models.response

import com.google.gson.annotations.SerializedName
import com.pablogd.data.models.MovieModel

data class MovieResponse(
    val page: Int,
    val results: List<MovieModel>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)
