package com.example.serierecomendator.data.model.user

data class MovieClass (
    val userRecomendator: String? = null, // Set default to null
    val language: String = "", // Set default to empty string
    val originalTitle: String = "",
    val title: String = "",
    val sinopsis: String = "",
    val id: Int = 0,
    val image: String = "",
    val opinion: String = ""
)