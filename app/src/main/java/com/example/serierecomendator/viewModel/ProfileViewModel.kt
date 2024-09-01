package com.example.serierecomendator.viewModel

import android.util.Log
import com.example.serierecomendator.repository.UserRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProfileViewModel() {
    val userName = MutableStateFlow("")
    val userIma = MutableStateFlow("")


    init {
        userInformation()
    }

    fun userInformation(){
        val userId = Firebase.auth.currentUser?.uid

        val ref = "user profile/${userId}.jpg"// Referencia de la imagen en Firebase Storage
        val ref2 = "user profile/null.jpg"
        var imageURL = ""
        val storageReference = Firebase.storage.getReference(ref2)

        UserRepository().getUserById(){user ->
            user?.let {
                userName.value = user.displayName

                if(user.userImage == "user profile/null.jpg") {
                    storageReference.downloadUrl.addOnSuccessListener {// Obtiene la URL de la imagen subida
                        imageURL = it.toString()
                        Log.d("image", "image: " + imageURL)
                        userIma.value = imageURL
                    }
                }
            }
        }
    }
}