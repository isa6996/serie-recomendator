package com.example.serierecomendator.data.model.retrofit

data class ResultTv(
     val id: Int,
     val overview: String,
     val poster_path: String,
     val original_language: String,
    val original_name: String,
    val name: String,
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val popularity: Double,
     val first_air_date: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)