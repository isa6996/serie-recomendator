package com.example.serierecomendator.repository

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.serierecomendator.data.model.classes.MovieClass
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.time.Instant

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideMovieFirebaseRepository(): MovieFirebaseRepository {
        return MovieFirebaseRepository()
    }
    @Provides
    fun provideUserRepository(): UserRepository {
        return UserRepository() // Asegúrate de que UserRepository tenga un constructor disponible
    }
}
class MovieFirebaseRepository {
    val db = FirebaseFirestore.getInstance()
    val currentUser = Firebase.auth.currentUser
    val userId = currentUser?.uid

    fun getAllRecomendatedMoviesFB(): Flow<List<MovieClass?>> {
        return flow {
            val snapshot = db.collection("movies")
                .get().await()
            val movies = snapshot.toObjects(MovieClass::class.java)
            emit(movies)
        }.catch { throwable ->
            Log.d("prueba", "Error: $throwable")
            println("Error fetching movies: $throwable")
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createMovies(
        language: String,
        originalTitle: String,
        title: String,
        synopsis: String,
        id: String,
        image: String,
        type: String,
        opinion: String
    ) {
        val userDisplayNameId= userId
        val currentTimeInSeconds = Instant.now().epochSecond
        val movie = MovieClass(userId, language, originalTitle,title, synopsis, id, image,type,
            opinion, currentTimeInSeconds)
Log.d("serieInq", "user: " + userDisplayNameId)
        Log.d("serieInq", "movie: " + movie)
        db.collection("movies").add(movie).addOnCompleteListener {
            task ->
            if (task.isSuccessful) {
                Log.d("serieInq", "creada la serie" + movie.originalTitle)
            } else {
                Log.d("serieInq", "serie:failure ", task.exception)
            }
        }
        Log.d("serieInq", "user: " + userDisplayNameId)

    }
}