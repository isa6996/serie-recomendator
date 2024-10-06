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
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await


class UserRepository {

    val db = FirebaseFirestore.getInstance()
    val currentUser = Firebase.auth.currentUser
    val currentUserId = currentUser?.uid

    fun createUserRepository(
        userId: String,
        displayName: String,
        userImage: String
    ) = runBlocking {
        Log.d("create User", "createUserWithEmail:success")
        val user = UserClass().apply {
            this.userId = userId
            this.displayName = displayName
        }

        val ref = "user profile/null.jpg"
        val storageReference = Firebase.storage.getReference(ref)

        try {
            val uri = storageReference.downloadUrl.await()
            val imageURL = uri.toString()
            Log.d("image", "Image URL: $imageURL")
            user.userImage = imageURL
            Log.d("image", "userImage URL: ${user.userImage}")

            db.collection("users").document(userId).set(user).await()
        } catch (e: Exception) {
            Log.d("create User", "createUserWithEmail:failure", e)
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

                    Log.d("crearid", "tienen el mismo id: " + user?.userId)
                    Log.d("crearid", "tienen el mismo id: " + currentUserId.toString())

                    Log.d("User", "user: " + user?.displayName)
                    Log.d("User", "userid " + user?.userId)
                }
            }
    }

    fun updateUser(newDisplayName: String?, imageUri: Uri?) {
        if (currentUserId != null) {

            db.collection("users")
                .whereEqualTo("userId", currentUserId)
                .get()
                .addOnSuccessListener {
                    val user = it.firstOrNull()?.toObject(UserClass::class.java)
                    if (!newDisplayName.isNullOrEmpty()) {
                        user?.displayName = newDisplayName.toString()
                    }

                    if (imageUri != null) {
                        val ref = "user profile/${currentUserId}.jpg"
                        val storageReference = Firebase.storage.getReference(ref)
                        storageReference.putFile(imageUri)
                            .addOnSuccessListener {
                                storageReference.downloadUrl.addOnSuccessListener { uri ->
                                    val imageURL = uri.toString()
                                    Log.d("image", "Image URL: $imageURL")
                                    user?.userImage = imageURL

                                    // Update Firestore with the new user data
                                    db.collection("users").document(currentUserId).set(user!!)
                                        .addOnSuccessListener {
                                            Log.d("User", "DocumentSnapshot successfully written!")
                                        }
                                        .addOnFailureListener { e ->
                                            Log.w("User", "Error writing document", e)
                                        }
                                }.addOnFailureListener { e ->
                                    Log.w("image", "Failed to get image URL", e)
                                }
                            }.addOnFailureListener { e ->
                                Log.w("image", "Failed to upload image", e)
                            }
                    } else {
                        // Update Firestore with the new display name only
                        db.collection("users").document(currentUserId).set(user!!)
                            .addOnSuccessListener {
                                Log.d("User", "DocumentSnapshot successfully written!")
                            }
                            .addOnFailureListener { e ->
                                Log.w("User", "Error writing document", e)
                            }
                    }
                }
                .addOnFailureListener { e ->
                    Log.w("User", "Error getting document", e)
                }
        }
    }
}