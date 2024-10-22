package com.example.serierecomendator.view

import android.util.Log
import androidx.compose.material3.Switch
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.serierecomendator.navigation.Screen
import com.example.serierecomendator.viewModel.SearchViewModel
import com.example.serierecomendator.viewModel.SettingsViewModel

@Composable
fun SettingsView(
    navController: NavHostController,
    settingsViewModel: SettingsViewModel = hiltViewModel(),
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    Text(text = "Settings")
    Column {
        LazyColumn {
            item {
                Button(onClick = {
                    navController.navigate(Screen.EditProfile.route)
                }) {
                    Text(text = "Change Profile")
                }
                Text(text = "Buscar webtoon en Español / Ingles")
                ToggleSwitch(searchViewModel)
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

@Composable
fun ToggleSwitch(viewModel: SearchViewModel) {
    val canvasIdiom by viewModel.canvasIdiom.observeAsState(true)
    Log.d("ToggleSwitch", "canvasIdiom: $canvasIdiom")
    Log.d("ToggleSwitch", "idioma :"+viewModel.canvasIdiom.value)
    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = if (canvasIdiom) "Español" else "Ingles")
        Switch(
            checked = canvasIdiom,
            onCheckedChange = { viewModel.toggleMode() }
        )
    }
}

