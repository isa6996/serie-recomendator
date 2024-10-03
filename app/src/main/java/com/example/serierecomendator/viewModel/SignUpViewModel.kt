package com.example.serierecomendator.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.serierecomendator.repository.UserRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor() : ViewModel() {

    private val auth: FirebaseAuth = Firebase.auth
    private val loadingUserExist = mutableStateOf(false)

    fun createUserWithEmailAndPassword(
        displayName: String,
        email: String,
        password: String
    ) {
        if (!loadingUserExist.value) {
            loadingUserExist.value = true
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        createUser(displayName)
                    } else {
                        Log.d("create User", "createUserWithEmail:failure ", task.exception)
                    }
                    loadingUserExist.value = false
                }
        }
    }

    private fun createUser(displayName: String) {
        Log.d("create User", "createUserWithEmail:success")
        UserRepository().createUserRepository(
            userId = auth.currentUser?.uid.toString(),
            displayName = displayName,
             userImage = ""
        )
    }
}
