package com.example.serierecomendator.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.serierecomendator.data.model.user.MovieClass
import com.example.serierecomendator.repository.MovieFirebaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecomendationListViewModel@Inject constructor(
    private val repo: MovieFirebaseRepository
) : ViewModel() {

    private val _recommendedMovies: MutableStateFlow<List<MovieClass?>> = MutableStateFlow(emptyList())
    val recommendedMovies: StateFlow<List<MovieClass?>> = _recommendedMovies

    init {
        getAllRecommendedMovies()
    }

     fun getAllRecommendedMovies() {

        viewModelScope.launch {
            Log.d("serieInq", "a veeer: " + repo.getAllRecomendatedMoviesFB())

            repo.getAllRecomendatedMoviesFB().collect { movies ->
                _recommendedMovies.value = movies
                Log.d("serieInq", "recomendated "+_recommendedMovies.value.toString())
            }
            Log.d("RecomendationListViewModel", "recomendated M : " + _recommendedMovies.toString())

        }
    }

}