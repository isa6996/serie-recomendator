package com.example.serierecomendator.data.model.classes

sealed class MediaType {
    object Tv : MediaType()
    object Movie : MediaType()
    object Manga : MediaType()
    object Webtoon : MediaType()
    object Novel : MediaType()
}
