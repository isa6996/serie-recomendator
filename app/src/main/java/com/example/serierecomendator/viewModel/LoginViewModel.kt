package com.example.serierecomendator.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavHostController
import com.example.serierecomendator.navigation.Screen
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel() {
    private val auth= FirebaseAuth.getInstance()

    fun  loginUser(email: String, password: String, onLoginSuccess: (String) -> Unit) {

        if (email.isNotEmpty() && password.isNotEmpty()) {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("Login", "signInWithEmail:success")

                        onLoginSuccess(auth.currentUser!!.email!!)
                        isLoggedIn()

                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("Login", "signInWithEmail:failure", task.exception)

                    }
                }
        }

    }

    fun isLoggedIn(): Boolean {
        return auth.currentUser != null
    }

}