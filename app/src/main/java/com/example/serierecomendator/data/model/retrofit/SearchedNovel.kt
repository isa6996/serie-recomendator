package com.example.serierecomendator.data.model.retrofit

data class SearchedNovel(
    val data: List<ResultNovel>?,
    val error: Boolean,
    val message: Any
)