package com.pablogd.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VideoModel(
    val id: String,
    @Json(name = "iso_3166_1") val iso31661: String,
    @Json(name = "iso_639_1") val iso6391: String,
    val key: String,
    val name: String,
    val site: String,
    val size: Int,
    val type: String
)