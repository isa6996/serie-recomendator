package com.example.serierecomendator.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.serierecomendator.data.model.classes.MovieClass
import com.example.serierecomendator.data.model.classes.UserClass
import com.example.serierecomendator.repository.MovieFirebaseRepository
import com.example.serierecomendator.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

@HiltViewModel
class RecomendationListViewModel@Inject constructor(
    private val repo: MovieFirebaseRepository,
    private val userRepo: UserRepository
) : ViewModel() {

    private val _recommendations: MutableStateFlow<List<Pair<MovieClass?, UserClass?>>> =
        MutableStateFlow(emptyList())
    // Exponemos recomendaciones como StateFlow
    val recommendations: StateFlow<List<Pair<MovieClass?, UserClass?>>> get() = _recommendations


    init {
        getAllRecommendedMovies()
    }

    fun getAllRecommendedMovies() {

        viewModelScope.launch {
            Log.d("serieInq", "a veeer: " + repo.getAllRecomendatedMoviesFB())

            repo.getAllRecomendatedMoviesFB().collect { movies ->
                _recommendations.value = movies.map { movie ->
                    val user = getUserByIdAsync(movie?.userRecomendator ?: "")
                    Pair(movie, user)
                }
                Log.d("serieInq", "recomendated " + _recommendations.toString())
            }
            Log.d("RecomendationListViewModel", "recomendated M : " + _recommendations.toString())

        }
    }

    private suspend fun getUserByIdAsync(userId: String): UserClass? {
        return suspendCancellableCoroutine { continuation ->
            // Llamamos a getUserById y pasamos un callback que reanuda la coroutine
            userRepo.getUserById(userId) { user ->
                if (continuation.isActive) {
                    continuation.resume(user)
                } else {
                    continuation.resumeWithException(CancellationException("Continuation is not active"))
                }

            }

        }
    }
}

