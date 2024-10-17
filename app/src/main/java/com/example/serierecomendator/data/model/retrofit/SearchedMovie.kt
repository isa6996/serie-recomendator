package com.example.serierecomendator.data.model.retrofit

data class SearchedMovie(
    val page: Int,
    val results: List<ResultMovies>,
    val total_pages: Int,
    val total_results: Int
)