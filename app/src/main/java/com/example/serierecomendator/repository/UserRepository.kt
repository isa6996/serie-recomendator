package com.example.serierecomendator.repository

import android.net.Uri
import android.util.Log
import com.example.serierecomendator.data.model.user.UserClass
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.tasks.await


class UserRepository {

    val db = FirebaseFirestore.getInstance()
    val currentUser = Firebase.auth.currentUser
    val currentUserId = currentUser?.uid

    fun createUserRepository(
        userId: String,
        displayName: String,
    ) {
        Log.d("create User", "createUserWithEmail:success")
        val user = UserClass()
        userId.let { user.userId = it }
        displayName.let { user.displayName = it }

        db.collection("users").add(user).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d("create User", "deberia salir" + db.toString())
            } else {
                Log.d("create User", "createUserWithEmail:failure ", task.exception)
            }
        }

    }

    fun getUserById(userId: String ,callback: (UserClass?) -> Unit) {
        db.collection("users")
            .whereEqualTo("userId", userId)
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val document = task.result?.documents
                    val user = document?.firstOrNull()?.toObject(UserClass::class.java)
                    callback(user)
                    Log.d("User", "user: " + user?.displayName)
                    Log.d("User", "userid " + user?.userId)
                    Log.d("User", "id current user " + currentUserId.toString())
                }
                Log.d("User", "user despues del if : " + currentUserId.toString())
            }
    }

    fun updateUser(newDisplayName: String?, imageUri: Uri?) {
        if (currentUserId != null) {

            db.collection("users")
                .whereEqualTo("userId", currentUserId)
                .get()
                .addOnSuccessListener {
                    val user = it.firstOrNull()?.toObject(UserClass::class.java)
                    if (newDisplayName != user?.displayName) {
                        user?.displayName = newDisplayName.toString()
                    }
                    else {
                        user?.displayName = user?.displayName.toString()
                    }
                    if (imageUri != null) {
                        val ref = "user profile/${currentUserId}.jpg"
                        val storageReference = Firebase.storage.getReference(ref)
                        storageReference.putFile(imageUri)
                            .addOnSuccessListener {
                                storageReference.downloadUrl.addOnSuccessListener { uri -> // Obtiene la URL de la imagen subida}
                                    val imageURL = uri.toString()
                                    user?.userImage = imageURL
                                }
                            }
                        user?.userImage = storageReference.toString()
                        Log.d("User", "referencia qie deberioa ser  : " + storageReference.toString())
                        Log.d("User", "referencia qie es  : " + user?.userImage)

                    }
                    db.collection("users").document(currentUserId).set(user!!)

                }
                .addOnSuccessListener { Log.d("User", "DocumentSnapshot successfully written!") }
                .addOnFailureListener { e -> Log.w("User", "Error writing document", e) }
        }
    }

    fun getUserImage(userId: String?, refImage: String, callback: (String) -> Unit) {
        val ref = "user profile/${userId ?: "null"}.jpg" // Handle null userId
        val ref2 = "user profile/null.jpg"
        val storageReference = Firebase.storage.getReference(if (refImage == ref2) ref2 else ref)

        storageReference.downloadUrl.addOnSuccessListener { uri ->
            val imageURL = uri.toString()
            Log.d("image", "Image URL: $imageURL")
            callback(imageURL)
        }.addOnFailureListener {
            Log.d("image", "Failed to get image URL")
            callback("") // Return an empty string or handle the error as needed
        }
    }

}