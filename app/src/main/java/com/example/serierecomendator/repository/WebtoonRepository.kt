package com.example.serierecomendator.repository

import android.util.Log
import com.example.serierecomendator.data.model.retrofit.ResultWebtoon
import com.example.serierecomendator.network.RapidApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WebtoonRepository @Inject constructor(
    private val rapidApi: RapidApi
) {
    suspend fun getWebtoon(title: String): ResultWebtoon? {
        try {
            return withContext(Dispatchers.IO) {
                val response = rapidApi.searchCanvasWebtoon(0, 20, title)
                Log.d("RapidApi", "Success: ${response.body()}")
                if (response.isSuccessful) {
                    response.body() // Esto devuelve null si el cuerpo es null

                } else {
                    Log.e("RapidApi", "Error: ${response.errorBody()?.string()}")
                    null
                }
            }
        } catch (we: Exception) {
            Log.e("RapidApi", "Error: ${we.message}")
            Log.e("RapidApi", "Error: ${we.message}")
            return null as ResultWebtoon
        }
    }

}