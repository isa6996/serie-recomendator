package com.example.serierecomendator.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.serierecomendator.repository.UserRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(): ViewModel() {
    val userName = MutableStateFlow("")
    val userIma = MutableStateFlow("")


    init {
        userInformation()
    }

    fun userInformation() {
        val userId = Firebase.auth.currentUser?.uid

        Log.d("crearid", userId.toString())

        UserRepository().getUserById(userId.toString()) { user ->
            user?.let {
                userName.value = user.displayName
                userIma.value = user.userImage
            }
        }
    }
}
