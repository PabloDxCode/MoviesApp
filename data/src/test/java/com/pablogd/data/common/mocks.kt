package com.pablogd.data.common

import com.pablogd.domain.models.Movie
import com.pablogd.domain.models.TvShow
import com.pablogd.domain.models.Video

val mockedMovie = Movie(
    0,
    12312,
    "Title",
    "Overview",
    "01/06/2021",
    "Poster",
    "",
    "EN",
    "Title",
    8.0,
    8.0,
    false
)

val mockedTvShow = TvShow(
    0,
    211232,
    "Name",
    "Overview",
    "Poster",
    "",
    "01/06/2021",
    "EN",
    "Name",
    7.8,
    9.0,
    2000
)

val mockedVideos = Video(
    "0",
    "MX",
    "es",
    "WUvq_9XW2Do",
    "Name",
    "YouTube",
    1080,
    "Trailer"
)