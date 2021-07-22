package com.pablogd.data.models.response

import com.pablogd.data.models.TvShowModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TvShowsResponse(
    val page: Int,
    val results: List<TvShowModel>,
    @Json(name = "total_pages") val totalPages: Int,
    @Json(name = "total_results") val totalResults: Int
)
