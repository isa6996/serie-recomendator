package com.example.serierecomendator.viewModel

import android.net.Uri
import com.example.serierecomendator.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow

class SettingsViewModel {
    val userRepositoy = UserRepository()
    val auth = FirebaseAuth.getInstance()
    val displayName= MutableStateFlow("")
    val imageUser= MutableStateFlow<Uri?>(null)

    fun updateCurrentUser(newDisplayName:String?, newImageUser:Uri?) {
        if (newDisplayName != null|| newImageUser != null) {


            userRepositoy.updateUser(newDisplayName, newImageUser)
        }
    }

    fun LogOut() {
        auth.signOut()

    }
}