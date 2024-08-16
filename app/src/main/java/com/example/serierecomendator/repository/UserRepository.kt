package com.example.serierecomendator.repository

import android.util.Log
import com.example.serierecomendator.data.model.user.UserClass
import com.google.firebase.firestore.FirebaseFirestore

class UserRepository {

    val db = FirebaseFirestore.getInstance()

    fun createUserRepository(
        userId: String,
        displayName: String,
    ){
        Log.d("create User", "createUserWithEmail:success")
        val user = UserClass()
        userId.let { user.userId = it }
        displayName.let { user.displayName = it }


        db.collection("users").add(user).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d("create User", "deberia salir"+ db.toString())
            } else {
                Log.d("create User", "createUserWithEmail:failure ", task.exception)
            }
        }

    }


}