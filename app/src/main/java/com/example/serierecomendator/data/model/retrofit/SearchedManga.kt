package com.example.serierecomendator.data.model.retrofit

data class MangaSearchList(
    val `data`: List<ResultMangas>,
    val limit: Int,
    val offset: Int,
    val response: String,
    val result: String,
    val total: Int
)
