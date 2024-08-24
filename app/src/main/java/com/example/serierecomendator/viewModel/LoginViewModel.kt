package com.example.serierecomendator.viewModel

import android.util.Log
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel() {
    private val auth= FirebaseAuth.getInstance()

    fun loginUser(email: String, password: String) {
        if (!email.isEmpty() && !password.isEmpty()) {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("Login", "signInWithEmail:success")
                        
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("Login", "signInWithEmail:failure", it.exception)
                    }
                }
        }

    }

}