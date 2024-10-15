package com.example.serierecomendator.data.model.retrofit

data class ResultMovies(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val first_air_date: String,  // Para series
    val original_name: String,  // Para series
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val name: String,  // Para series
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int,
)
