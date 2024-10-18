package com.example.serierecomendator.network

import com.example.serierecomendator.data.model.retrofit.MangaSearchList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MangaDexApi {
    @GET("manga?")
    suspend fun searchManga(
        @Query("title") title: String,
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0,
        @Query("includes[]") includes: List<String> = listOf("cover_art")
    ): Response<MangaSearchList>
}