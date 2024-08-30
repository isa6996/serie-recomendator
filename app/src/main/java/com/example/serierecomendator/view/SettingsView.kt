package com.example.serierecomendator.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.serierecomendator.navigation.Screen
import com.example.serierecomendator.viewModel.SettingsViewModel

@Composable
fun SettingsView(navController: NavHostController) {
    val settingsViewModel = SettingsViewModel()
    
    Text(text = "Settings")
    Column {
        LazyColumn {
            item {
                Button(onClick = {
                    settingsViewModel.LogOut()
                    navController.navigate(Screen.Login.route)
                }
                ) {
                    Text(text = "Log Out")
    
                }

            }
        }
    }
}