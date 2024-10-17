package com.example.serierecomendator.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.serierecomendator.data.model.classes.FinalSearchResult
import com.example.serierecomendator.data.model.classes.MediaType
import com.example.serierecomendator.data.model.retrofit.ResultMovies
import com.example.serierecomendator.data.model.retrofit.ResultTv
import com.example.serierecomendator.repository.MovieFirebaseRepository
import com.example.serierecomendator.repository.RecommendationRepository
import com.example.serierecomendator.repository.RecommendationRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
 * sirve para hacer la llamada a la api de MovieDB y obtener las películas recomendadas
 * el inject sirve para inyectar dependencias de la clase RecommendationRepository
 */
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: RecommendationRepository,
    private val movieRepository2: RecommendationRepositoryImpl


): ViewModel() {
    private val movieRepository: MovieFirebaseRepository = MovieFirebaseRepository()

    private val _tvSeries =MutableLiveData<List<ResultTv>>()
    val tvSeries: LiveData<List<ResultTv>> get() = _tvSeries

    private val _movies =MutableLiveData<List<ResultMovies>>()
    val movies: LiveData<List<ResultMovies>> get() = _movies

    private val _finalSearchResults = MutableLiveData<List<FinalSearchResult>>()
    val finalSearchResults: LiveData<List<FinalSearchResult>> get() = _finalSearchResults


    fun searchTitle(title: String, type: MediaType) {
        val searchedTitle = title.replace(" ", "+")
        viewModelScope.launch {
            when (type) {
                is MediaType.Tv -> {
                    val response = repository.getTvSeries(searchedTitle, "tv")
                    _tvSeries.value = response.results
                    transformMediaIntoList(type)
                }
                is MediaType.Movie -> {
                    val response = movieRepository2.getMovies(searchedTitle, "movie")
                    _movies.value = response.results
                    transformMediaIntoList(type)
                }
                is MediaType.Manga -> {
                    TODO()
                }
                is MediaType.Webtoon -> {
                    TODO()
                }
                is MediaType.Novel -> {

                }
            }
        }
    }

    fun transformMediaIntoList(type: MediaType) {
        _finalSearchResults.value = when (type) {
            is MediaType.Tv -> _tvSeries.value?.map { resultTv ->
                FinalSearchResult(
                    title = resultTv.name ,
                    originalTitle = resultTv.original_name,
                    overview = resultTv.overview,
                    posterPath = resultTv.poster_path ?: "",
                    releaseDate = resultTv.first_air_date ?: "",
                    type = type.toString()
                )
            } ?: emptyList()
            is MediaType.Movie -> _movies.value?.map { resultMovie ->
                FinalSearchResult(
                    title = resultMovie.title,
                    originalTitle = resultMovie.original_title,
                    overview = resultMovie.overview,
                    posterPath = resultMovie.poster_path ?: "",
                    releaseDate = resultMovie.release_date ?: "",
                    type = type.toString()
                )
            } ?: emptyList()
            else -> emptyList()
        }
    }


  /*  fun insertMovie(movie: ResultMovies, recommendationText: String) {
    val moviePoster=urlStringMovie+movie.poster_path
    movieRepository.createMovies(movie.original_language, movie.original_title, movie.title,
        movie.overview, movie.id, moviePoster, recommendationText)
}

fun insertMovie(serieTv: ResultTv, recommendationText: String) {
    val moviePoster=urlStringMovie+serieTv.poster_path

    movieRepository.createMovies(serieTv.original_language, serieTv.original_name, serieTv.name,
        serieTv.overview, serieTv.id, moviePoster, recommendationText)
    }*/
}