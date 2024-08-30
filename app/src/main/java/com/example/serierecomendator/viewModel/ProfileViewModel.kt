package com.example.serierecomendator.viewModel

import com.example.serierecomendator.repository.UserRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProfileViewModel() {
    val userName = MutableStateFlow("")
    val userIma = MutableStateFlow("")

    init {
        userInformation()
    }

    private fun userInformation(){
        UserRepository().getUserById(){user ->
            user?.let {
                userName.value = user.displayName
                userIma.value = user.userImage
            }
        }
    }
}