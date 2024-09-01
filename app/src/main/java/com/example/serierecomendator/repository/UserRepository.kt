package com.example.serierecomendator.repository

import android.util.Log
import com.example.serierecomendator.data.model.user.UserClass
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class UserRepository {

    val db = FirebaseFirestore.getInstance()
    val currentUser = Firebase.auth.currentUser
    val currentUserId= currentUser?.uid

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

    fun getUserById(callback: (UserClass?) -> Unit) {
        db.collection("users")
            .whereEqualTo("userId", currentUserId)
            .get()
            .addOnCompleteListener {task ->
                if (task.isSuccessful) {
                    val document = task.result?.documents
                    val user = document?.firstOrNull()?.toObject(UserClass::class.java)
                    callback(user)
                    Log.d("User", "user: " + user?.displayName)
                    Log.d("User", "userid " + user?.userId)
                    Log.d("User", "id current user " + currentUserId.toString())
                }
                Log.d("User", "user despues del if : " +currentUserId.toString())
            }
    }
}