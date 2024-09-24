package com.example.serierecomendator.viewModel

import android.graphics.Movie
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.serierecomendator.data.model.retrofit.Result
import com.example.serierecomendator.repository.RecommendationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
 * sirve para hacer la llamada a la api de MovieDB y obtener las películas recomendadas
 * el inject sirve para inyectar dependencias de la clase RecommendationRepository
 */
@HiltViewModel
class RecommendationViewModel @Inject constructor(
    private val repository: RecommendationRepository
): ViewModel() {

    private val _movies =MutableLiveData<List<Result>>()
    val movies: LiveData<List<Result>> get() = _movies

    /*init {
        viewModelScope.launch{
            try {
                repository.getMovies("dungeon+meshi")
                var response = repository.getMovies("dungeon+meshi")
                _movies.value = response.results
                Log.d("MovieDBAPI", "llamada: " + response.results)
                Log.d("MovieDBAPI", "Repository: " + repository.getMovies("dungeon+meshi"))
                Log.d("MovieDBAPI", "valor primero : " + response.results[0].name)
                Log.d("MovieDBAPI", "valor primero : " + response.results[1].name)

                Log.d("MovieDBAPI", "valor segundo : " + movies.value?.get(0)?.name)

            }catch (e: Exception){
                Log.d("MovieDBAPI", "Error: " + e.message)
                Log.d("MovieDBAPI", "Error: " + e.stackTrace)
                Log.d("MovieDBAPI", "Error: " + e.cause)
            }
        }

    }*/

    fun TitleToSearch(title: String){
        val searchedTitleSerie = title.replace(" ", "+")
        viewModelScope.launch {
            try {
                val searchResults = repository.getMovies(searchedTitleSerie)
                _movies.value = searchResults.results
                Log.d("MovieDBAPI", "llamada: " + searchResults.results)
            }catch ( e: Exception){
                Log.d("MovieDBAPI", "Error: " + e.message)
                Log.d("MovieDBAPI", "Error: " + e.stackTrace)
                Log.d("MovieDBAPI", "Error: " + e.cause)

            }catch (e: IndexOutOfBoundsException) {
                Log.e("MovieDBAPI", "Error de índice: ${e.message}", e)
                // Maneja el error de índice de forma más específica
            }
        }
        Log.d("buscador", "valor : " + searchedTitleSerie)
    }


}