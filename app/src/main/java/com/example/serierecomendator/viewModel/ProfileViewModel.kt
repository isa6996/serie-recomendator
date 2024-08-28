package com.example.serierecomendator.viewModel

import com.google.firebase.auth.FirebaseAuth

class ProfileViewModel {
    val auth = FirebaseAuth.getInstance()

    fun LogOut() {
        auth.signOut()

    }

}