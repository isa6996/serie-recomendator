package com.example.serierecomendator.data.model.retrofit

data class SearchedWebtoon(
    val display: Int,
    val query: String,
    val start: Int,
    val titleList: List<WebtoonTitle>,
    val total: Int
)