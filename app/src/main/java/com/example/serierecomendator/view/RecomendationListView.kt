package com.example.serierecomendator.view

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextField
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.serierecomendator.viewModel.RecomendationListViewModel
import kotlinx.coroutines.delay

@SuppressLint("SuspiciousIndentation")
@Composable
fun RecomendationListView(navController: NavHostController,
                          recomendationVM: RecomendationListViewModel = hiltViewModel()) {
    val recommendations by recomendationVM.recommendations.collectAsState()

    var showLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(3000) // Espera 3 segundos
        showLoading = false
    }


    Column {
        Text(text = "RecomendationListView")
        if (!recommendations.isNullOrEmpty()) {
            LazyColumn {
                items(recommendations.size) { index -> // Aquí pasamos el tamaño de la lista
                    // Obtenemos el par correspondiente usando el índice
                    val (movie, user) = recommendations[index]

                        Card (
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                        ){
                            Row (
                                modifier = Modifier.fillMaxWidth()
                            ){
                              if(user?.userImage != "user profile/null.jpg") {
                                  AsyncImage(
                                      model = user?.userImage, contentDescription = null,
                                      modifier = Modifier
                                          .height(50.dp)
                                          .clip(RoundedCornerShape(8.dp))
                                  )
                              }
                                Spacer(modifier = Modifier.width(8.dp)) // Add spacing between image and text
                                Column( // Use Column to stack text elements vertically
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(text = user!!.displayName)
                                    Text(text = "Tipo de serie (Serie o Pelicula?)")
                                    Text(
                                        text = movie!!.title,
                                        style = MaterialTheme.typography.titleMedium
                                        )

                                    Spacer(modifier = Modifier.height(16.dp))

                                    Button(onClick = {
                                        val movieId = movie.id
                                        navController.navigate("movie_details_screen/$movieId")
                                    })
                                    {
                                        Text(text = "Ver mas info")
                                    }
                                }
                            }

                        }
                        Spacer(modifier = Modifier.height(8.dp))
                }

            }
        } else {
            if (!showLoading) {
                Text(text = "No hay recomendaciones...")
            }
            else {
                Log.d("RecomendationListView", "algo no va bien...")
                Text(text = "Cargando recomendaciones...")
            }
        }

    }
}
