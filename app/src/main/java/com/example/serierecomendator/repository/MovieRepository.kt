package com.example.serierecomendator.repository

import android.util.Log
import com.example.serierecomendator.data.model.user.MovieClass
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class MovieFirebaseRepository {
    val db = FirebaseFirestore.getInstance()
    val currentUser = Firebase.auth.currentUser

    fun getMovies() {
        db.collection("movies").get()
    }

    fun createMovies(
        language: String,
        originalTitle: String,
        title: String,
        sinopsis: String,
        //image: String
    ) {
        val userDisplayNameId= currentUser?.uid
        val movie = MovieClass(currentUser?.uid, language, originalTitle, title, sinopsis, "null")

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