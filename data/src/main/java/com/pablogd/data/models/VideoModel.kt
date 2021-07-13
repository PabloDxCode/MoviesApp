package com.pablogd.data.models

import com.google.gson.annotations.SerializedName

data class VideoModel(
    val id: String,
    @SerializedName("iso_3166_1") val iso31661: String,
    @SerializedName("iso_639_1") val iso6391: String,
    val key: String,
    val name: String,
    val site: String,
    val size: Int,
    val type: String
)