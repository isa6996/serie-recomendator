package com.example.serierecomendator.data.model.retrofit

data class ResultMovies(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
     val id: Int,
     val original_language: String,
     val release_date: String,  // Para series
     val original_title: String,  // Para series
     val overview: String,
    val popularity: Double,
     val poster_path: String,
    val title: String,  // Para series
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int,
)


    //(id,  overview,poster_path,original_language, original_name, name,first_air_date)
