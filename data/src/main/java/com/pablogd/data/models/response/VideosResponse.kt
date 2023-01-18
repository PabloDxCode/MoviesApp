package com.pablogd.data.models.response

import com.pablogd.data.models.VideoModel
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VideosResponse(
    val id: Int,
    val results: List<VideoModel>
)
