package com.example.serierecomendator.view

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.serierecomendator.navigation.Screen
import com.example.serierecomendator.viewModel.SettingsViewModel
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ChangeProfileView(navController: NavHostController) {

    val settingsViewModel: SettingsViewModel = hiltViewModel()
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var newDisplayName by remember { mutableStateOf("") }


    val launcher =// launcher to select image
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri: Uri? ->
            uri?.let {
                imageUri = it
                settingsViewModel.imageUser.value = it
            }
        }


    Text(text = "Settings")
    Column {
        LazyColumn {
            item {

                imageUri.let {
                    Image(
                        painter = rememberAsyncImagePainter(model = it),
                        contentDescription = "",
                        modifier = Modifier.size(200.dp)
                    )

                }
                OutlinedTextField(
                    value = newDisplayName,
                    onValueChange = { newDisplayName = it },
                    label = { Text("stringResource(id = R.string.userName)") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )

                Button(onClick = { launcher.launch("image/*") } ,
                    modifier= Modifier
                        .fillMaxWidth(),) {
                    Text("stringResource(id = R.string.selectImage)")
                }


                Button(onClick = {
                    settingsViewModel.updateCurrentUser(newDisplayName, imageUri)
                }
                ) {
                    Text(text = "Update")

                }

            }
        }
    }
}