package com.pablogd.data.responses

import com.google.gson.Gson
import com.pablogd.data.models.response.MovieResponse
import com.pablogd.data.models.response.TvShowsResponse
import com.pablogd.data.models.response.VideosResponse

const val MOVIES_RESPONSE_CODE = 1
const val TV_SHOWS_RESPONSE_CODE = 2
const val SEARCH_MOVIES_RESPONSE_CODE = 3
const val SEARCH_TV_SHOWS_RESPONSE_CODE = 4
const val VIDEOS_RESPONSE_CODE = 5

private val moviesResponse = "{\n" +
        "  \"page\": 1,\n" +
        "  \"results\": [\n" +
        "    {\n" +
        "      \"adult\": false,\n" +
        "      \"backdrop_path\": \"/5C8bfwglg91uZhc2fbfpSjNGamV.jpg\",\n" +
        "      \"genre_ids\": [\n" +
        "        27,\n" +
        "        9648\n" +
        "      ],\n" +
        "      \"id\": 591273,\n" +
        "      \"original_language\": \"en\",\n" +
        "      \"original_title\": \"Fear Street: 1994\",\n" +
        "      \"overview\": \"En 1994, un grupo de adolescentes descubre que los terribles sucesos que han asolado su ciudad durante generaciones están conectados y que pueden ser los próximos objetivos.\",\n" +
        "      \"popularity\": 1607.093,\n" +
        "      \"poster_path\": \"/xGf1snRLGQpiUkWvJSGwiSXW9sR.jpg\",\n" +
        "      \"release_date\": \"2021-07-02\",\n" +
        "      \"title\": \"La calle del terror, Parte 1: 1994\",\n" +
        "      \"video\": false,\n" +
        "      \"vote_average\": 6.8,\n" +
        "      \"vote_count\": 493\n" +
        "    }\n" +
        "  ],\n" +
        "  \"total_pages\": 500,\n" +
        "  \"total_results\": 10000\n" +
        "}"

private val tvShowResponse = "{\n" +
        "    \"page\": 1,\n" +
        "    \"results\": [\n" +
        "        {\n" +
        "            \"backdrop_path\": \"/xxlteGxXhpgnoMF7SvdYwHvljnB.jpg\",\n" +
        "            \"first_air_date\": \"2021-06-09\",\n" +
        "            \"genre_ids\": [\n" +
        "                18,\n" +
        "                10765\n" +
        "            ],\n" +
        "            \"id\": 84958,\n" +
        "            \"name\": \"Loki\",\n" +
        "            \"origin_country\": [\n" +
        "                \"US\"\n" +
        "            ],\n" +
        "            \"original_language\": \"en\",\n" +
        "            \"original_name\": \"Loki\",\n" +
        "            \"overview\": \"After stealing the Tesseract during the events of “Avengers: Endgame,” an alternate version of Loki is brought to the mysterious Time Variance Authority, a bureaucratic organization that exists outside of time and space and monitors the timeline. They give Loki a choice: face being erased from existence due to being a “time variant”or help fix the timeline and stop a greater threat.\",\n" +
        "            \"popularity\": 9088.887,\n" +
        "            \"poster_path\": \"/kEl2t3OhXc3Zb9FBh1AuYzRTgZp.jpg\",\n" +
        "            \"vote_average\": 8.2,\n" +
        "            \"vote_count\": 6233\n" +
        "        }\n" +
        "    ],\n" +
        "    \"total_pages\": 500,\n" +
        "    \"total_results\": 10000\n" +
        "}"

private val searchMoviesResponse = "{\n" +
        "    \"page\": 1,\n" +
        "    \"results\": [\n" +
        "        {\n" +
        "            \"adult\": false,\n" +
        "            \"backdrop_path\": \"/vbY95t58MDArtyUXUIb8Fx1dCry.jpg\",\n" +
        "            \"genre_ids\": [\n" +
        "                28,\n" +
        "                878,\n" +
        "                12\n" +
        "            ],\n" +
        "            \"id\": 1726,\n" +
        "            \"original_language\": \"en\",\n" +
        "            \"original_title\": \"Iron Man\",\n" +
        "            \"overview\": \"El multimillonario fabricante de armas Tony Stark debe enfrentarse a su turbio pasado después de sufrir un accidente con una de sus armas. Equipado con una armadura de última generación tecnológica, se convierte en \\\"El hombre de hierro\\\" para combatir el mal a escala global.\",\n" +
        "            \"popularity\": 97.131,\n" +
        "            \"poster_path\": \"/tFCTNx7foAsUQpgu2x1KjAJD1wT.jpg\",\n" +
        "            \"release_date\": \"2008-04-30\",\n" +
        "            \"title\": \"Iron Man\",\n" +
        "            \"video\": false,\n" +
        "            \"vote_average\": 7.6,\n" +
        "            \"vote_count\": 20890\n" +
        "        }\n" +
        "    ],\n" +
        "    \"total_pages\": 31,\n" +
        "    \"total_results\": 603\n" +
        "}"

private val searchTvShowsResponse = "{\n" +
        "    \"page\": 1,\n" +
        "    \"results\": [\n" +
        "        {\n" +
        "            \"backdrop_path\": \"/xxlteGxXhpgnoMF7SvdYwHvljnB.jpg\",\n" +
        "            \"first_air_date\": \"2021-06-09\",\n" +
        "            \"genre_ids\": [\n" +
        "                18,\n" +
        "                10765\n" +
        "            ],\n" +
        "            \"id\": 84958,\n" +
        "            \"name\": \"Loki\",\n" +
        "            \"origin_country\": [\n" +
        "                \"US\"\n" +
        "            ],\n" +
        "            \"original_language\": \"en\",\n" +
        "            \"original_name\": \"Loki\",\n" +
        "            \"overview\": \"Loki, el impredecible villano Loki (Hiddleston) regresa como el Dios del engaño en una nueva serie tras los acontecimientos de Avengers\",\n" +
        "            \"popularity\": 9088.887,\n" +
        "            \"poster_path\": \"/kAHPDqUUciuObEoCgYtHttt6L2Q.jpg\",\n" +
        "            \"vote_average\": 8.2,\n" +
        "            \"vote_count\": 6233\n" +
        "        }\n" +
        "    ],\n" +
        "    \"total_pages\": 1,\n" +
        "    \"total_results\": 3\n" +
        "}"

private val videosResponse = "{\n" +
        "    \"id\": 1726,\n" +
        "    \"results\": [\n" +
        "        {\n" +
        "            \"id\": \"5b662c0e0e0a267ef808878a\",\n" +
        "            \"iso_639_1\": \"es\",\n" +
        "            \"iso_3166_1\": \"MX\",\n" +
        "            \"key\": \"WUvq_9XW2Do\",\n" +
        "            \"name\": \"Iron Man – El Hombre de Hierro (2008) Tráiler #1 Doblado al Latino\",\n" +
        "            \"site\": \"YouTube\",\n" +
        "            \"size\": 720,\n" +
        "            \"type\": \"Trailer\"\n" +
        "        }\n" +
        "    ]\n" +
        "}"

val responses = HashMap<Int, String>().apply {
    put(MOVIES_RESPONSE_CODE, moviesResponse)
    put(TV_SHOWS_RESPONSE_CODE, tvShowResponse)
    put(SEARCH_MOVIES_RESPONSE_CODE, searchMoviesResponse)
    put(SEARCH_TV_SHOWS_RESPONSE_CODE, searchTvShowsResponse)
    put(VIDEOS_RESPONSE_CODE, videosResponse)
}

val movieObjResponse = Gson().fromJson(moviesResponse, MovieResponse::class.java)
val tvShowsObjResponse = Gson().fromJson(tvShowResponse, TvShowsResponse::class.java)
val searchMoviesObjResponse = Gson().fromJson(searchMoviesResponse, MovieResponse::class.java)
val searchTvShowsObjResponse = Gson().fromJson(searchTvShowsResponse, TvShowsResponse::class.java)
val videosObjResponse = Gson().fromJson(videosResponse, VideosResponse::class.java)
