package com.example.serierecomendator.viewModel

import com.google.firebase.auth.FirebaseAuth

class SettingsViewModel {
    val auth = FirebaseAuth.getInstance()

    fun LogOut() {
        auth.signOut()

    }
}