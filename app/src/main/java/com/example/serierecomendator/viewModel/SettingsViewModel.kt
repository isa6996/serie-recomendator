package com.example.serierecomendator.viewModel

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.example.serierecomendator.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel@Inject constructor(): ViewModel() {
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