package com.example.serierecomendator.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.serierecomendator.repository.RecommendationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecommendationViewModel @Inject constructor(
    private val repository: RecommendationRepository
): ViewModel() {

    init {
        viewModelScope.launch{
            repository.getMovies()
            var response = repository.getMovies()
            Log.d("MovieDBAPI", "llamada: "+ response.page)
            Log.d("MovieDBAPI", "Repository: "+ repository.getMovies())
        }

    }

}