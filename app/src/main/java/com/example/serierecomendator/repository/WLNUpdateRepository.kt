package com.example.serierecomendator.repository

import android.util.Log
import com.example.serierecomendator.data.model.retrofit.SearchedNovel
import com.example.serierecomendator.network.WLNUpdatesApi
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class WLNUpdateRepository @Inject constructor(
    private val wlnUpdatesApi: WLNUpdatesApi
) {
    suspend fun getNovel(title: String): SearchedNovel? {
        try {
            Log.d("WLNUpdatesApi", "api : " + wlnUpdatesApi.toString())
            Log.d("WLNUpdatesApi", "title : " + title)
            Log.d("WLNUpdatesApi", "sara : " + wlnUpdatesApi.searchAdvanced(title.toString().toRequestBody()))
            return withContext(Dispatchers.IO) {
                val json = JSONObject().apply {
                    put("mode", "search-advanced")
                    put("sort-mode", "update")
                    put("include-results", JSONArray().apply {
                        put("description")
                        put("covers")
                    })
                    put("title-search-text", title) // Asegúrate de usar el parámetro correcto
                }
                val requestBody = json.toString().toRequestBody("application/json".toMediaTypeOrNull())
                val response = wlnUpdatesApi.searchAdvanced(requestBody)
                if (response.isSuccessful) {
                    response.body()
                } else {
                    Log.e("WLNUpdatesApi", "Error: ${response.errorBody()?.string()}")
                    null
                }
            }
        }catch (e: Exception){

            return null as SearchedNovel
        }
    }
}