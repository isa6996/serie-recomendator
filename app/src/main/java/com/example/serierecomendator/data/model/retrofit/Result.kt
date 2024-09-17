package com.example.serierecomendator.data.model.retrofit

data class Result(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_name: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val first_air_date: String,
    val name: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)