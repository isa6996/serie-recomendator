package com.example.serierecomendator.repository

import android.util.Log
import com.example.serierecomendator.data.model.retrofit.MangaSearchList
import com.example.serierecomendator.network.MangaDexApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MangaRecomendationRepository @Inject constructor(
    private val mangaDexApi: MangaDexApi
) {
    suspend fun getManga(title: String): MangaSearchList? {
        return try {
            Log.d("MangaDexApi", mangaDexApi.toString())
            withContext(Dispatchers.IO) {
                val response = mangaDexApi.searchManga(title,  20, 0, listOf("cover_art"))
                if (response.isSuccessful) {
                    response.body() // Esto devuelve null si el cuerpo es null
                } else {
                    Log.e("MangaDexApi", "Error: ${response.errorBody()?.string()}")
                    null
                }
            }
        } catch (e: Exception) {
            Log.e("MangaDexApi", "Error: ${e.message}")
            null
        }
    }
}