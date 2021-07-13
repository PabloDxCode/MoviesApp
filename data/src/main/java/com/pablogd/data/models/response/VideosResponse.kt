package com.pablogd.data.models.response

import com.pablogd.data.models.VideoModel

data class VideosResponse(
    val id: Int,
    val results: List<VideoModel>
)
