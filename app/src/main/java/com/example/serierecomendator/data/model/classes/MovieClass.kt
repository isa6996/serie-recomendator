package com.example.serierecomendator.data.model.classes

data class MovieClass(
    val userRecomendator: String? = null, // Set default to null
    val language: String = "", // Set default to empty string
    val originalTitle: String = "",
    val title: String = "",
    val sinopsis: String = "",
    val id: String = "",
    val image: String = "",
    val type: String = "",
    val opinion: String = "",
    val currentTimeInSeconds: Long= 0L
)