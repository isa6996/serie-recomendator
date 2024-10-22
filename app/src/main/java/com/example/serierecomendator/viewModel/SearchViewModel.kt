package com.example.serierecomendator.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.serierecomendator.data.model.classes.FinalSearchResult
import com.example.serierecomendator.data.model.classes.MediaType
import com.example.serierecomendator.data.model.retrofit.ResultMangas
import com.example.serierecomendator.data.model.retrofit.ResultMovies
import com.example.serierecomendator.data.model.retrofit.ResultNovel
import com.example.serierecomendator.data.model.retrofit.ResultTv
import com.example.serierecomendator.data.model.retrofit.URL_STRING_MANGA
import com.example.serierecomendator.data.model.retrofit.URL_STRING_MOVIE
import com.example.serierecomendator.data.model.retrofit.WebtoonTitle
import com.example.serierecomendator.repository.MangaRecomendationRepository
import com.example.serierecomendator.repository.MovieFirebaseRepository
import com.example.serierecomendator.repository.RecommendationRepository
import com.example.serierecomendator.repository.WLNUpdateRepository
import com.example.serierecomendator.repository.WebtoonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
 * sirve para hacer la llamada a la api de MovieDB y obtener las películas recomendadas
 * el inject sirve para inyectar dependencias de la clase RecommendationRepository
 */
private var lenguageToSearch: String ="es"

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: RecommendationRepository,
    private val mangaRepository: MangaRecomendationRepository,
    private val webtoonRepository: WebtoonRepository,
    private val novelRepository: WLNUpdateRepository
): ViewModel() {
    private val movieRepository: MovieFirebaseRepository = MovieFirebaseRepository()

    private val _tvSeries =MutableLiveData<List<ResultTv>?>()

    private val _movies =MutableLiveData<List<ResultMovies>>()

    private val _mangas = MutableLiveData<List<ResultMangas>>()

    private val _webtoons = MutableLiveData<List<WebtoonTitle>>()

    private val _novels = MutableLiveData<List<ResultNovel>>()


    private val _finalSearchResults = MutableLiveData<List<FinalSearchResult>>()
    val finalSearchResults: LiveData<List<FinalSearchResult>> get() = _finalSearchResults

    private val _canvasIdiom = MutableLiveData(true)
     val canvasIdiom: LiveData<Boolean> get() = _canvasIdiom

    fun toggleMode() {
        Log.d("SearchViewModel", "idioma antes toggled: ${lenguageToSearch}")
        Log.d("SearchViewModel", "canvasIdiom antes toggled: ${_canvasIdiom.value}")
        _canvasIdiom.value = _canvasIdiom.value!!.not()
        lenguageToSearch = if (_canvasIdiom.value!!) {
            "es"
        } else {
            "en"
        }

        Log.d("SearchViewModel", "canvasIdiom despues toggled: ${_canvasIdiom.value}")
    }

    fun searchTitle(title: String, type: MediaType) {
Log.d("idioma", "cuando le das a buscar: "+ lenguageToSearch)
        try {
            val searchedTitle = title.replace(" ", "+")
            Log.d("webtoon", _canvasIdiom.toString())
            Log.d("webtoon", _canvasIdiom.value.toString())
            viewModelScope.launch {
                when (type) {
                    is MediaType.Tv -> {
                        val response = repository.getTvSeries(searchedTitle, "tv")
                        _tvSeries.value = response.results
                        transformMediaIntoList(type)
                        Log.d("search", response.results.toString())
                        Log.d("search", finalSearchResults.toString())
                        Log.d("webtoon", lenguageToSearch)
                    }

                    is MediaType.Movie -> {
                        val response = repository.getMovies(searchedTitle, "movie")
                        _movies.value = response.results
                        transformMediaIntoList(type)
                    }

                    is MediaType.Manga -> {
                        val response = mangaRepository.getManga(searchedTitle)
                        Log.d("mangadex", response.toString())
                        _mangas.value = response?.data
                        Log.d("mangadex", response?.data.toString())
                        transformMediaIntoList(type)

                    }

                    is MediaType.Webtoon -> {
                        Log.d("webtoon", lenguageToSearch)

                        val response = webtoonRepository.getWebtoon(searchedTitle, lenguageToSearch)
                        _webtoons.value = response?.message?.result?.challengeSearch?.titleList
                        Log.d("webtoon", response?.message?.result?.challengeSearch?.titleList.toString())
                   //     Log.d("webtoon", response?.titleList.toString())
                        transformMediaIntoList(type)

                    }

                    is MediaType.Novel -> {
                        val response = novelRepository.getNovel(searchedTitle)
                        Log.d("buscar","novelas encontradas: ${response?.data?.size}" )
                        Log.d("wlnupdates", response?.data.toString())
                        Log.d("wlnupdates", response.toString())

                        _novels.value = response?.data ?: emptyList()
                        transformMediaIntoList(type)
                    }
                }
            }
        } catch (e: Exception) {
            Log.d("search", e.toString())
        }
    }

    fun transformMediaIntoList(type: MediaType) {
        _finalSearchResults.value = when (type) {
            is MediaType.Tv -> _tvSeries.value?.map { resultTv ->
                FinalSearchResult(
                    title = resultTv.name ,
                    originalTitle = resultTv.original_name,
                    overview = resultTv.overview,
                    posterPath = (URL_STRING_MOVIE + resultTv.poster_path) ?: "",
                    releaseDate = resultTv.first_air_date ?: "",
                    type = type.toString()
                )
            } ?: emptyList()
            is MediaType.Movie -> _movies.value?.map { resultMovie ->
                FinalSearchResult(
                    title = resultMovie.title,
                    originalTitle = resultMovie.original_title,
                    overview = resultMovie.overview,
                    posterPath = (URL_STRING_MOVIE + resultMovie.poster_path) ?: "",
                    releaseDate = resultMovie.release_date ?: "",
                    type = type.toString()
                )
            } ?: emptyList()
            is MediaType.Manga -> _mangas.value?.map { resultManga ->

                //val originalLeng= resultManga.attributes.originalLanguage
                val coverArtFileName = resultManga.relationships.find {
                    it.type == "cover_art" }?.attributes?.fileName ?: "No file name"
                Log.d("mangadex", "id de la cover"+coverArtFileName)
                FinalSearchResult(
                    title = resultManga.attributes.title.en ?: "",
                    originalTitle = resultManga.attributes.altTitles.find {
                        it.ja == resultManga.attributes.originalLanguage }?.ja ?: "",
                    overview = resultManga.attributes.description.es ?: "",
                    posterPath = (URL_STRING_MANGA + resultManga.id + "/" +
                            coverArtFileName+".512.jpg") ?: "",
                    releaseDate = resultManga.attributes.createdAt ?: "",
                    type = type.toString()

                )

            } ?: emptyList()
            is MediaType.Webtoon -> _webtoons.value?.map { resultWebtoon ->
                FinalSearchResult(
                    title = resultWebtoon.title,
                    originalTitle = resultWebtoon.title,
                    overview = "",
                    posterPath = "https://webtoon-phinf.pstatic.net"+resultWebtoon.thumbnail+"?type=q90",
                    releaseDate = "resultWebtoon.lastEpisodeRegisterYmdt.toString()",
                    type = type.toString()
                )

            } ?: emptyList()

            is MediaType.Novel -> _novels.value?.map { resultNovel ->
                FinalSearchResult(
                    title = resultNovel.title,
                    originalTitle = resultNovel.title,
                    overview = resultNovel.description ?:"",
                    posterPath = resultNovel.covers.firstOrNull()?.url ?: "",
                    releaseDate = resultNovel.latest_published.toString() ?: "",
                    type = type.toString()
                )
            }?: emptyList()
            else -> emptyList()
        }
    }




    /*fun insertMovie(movie: ResultMovies, recommendationText: String) {
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