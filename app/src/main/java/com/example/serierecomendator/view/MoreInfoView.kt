package com.example.serierecomendator.view


import android.util.Log
import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavArgs
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.serierecomendator.viewModel.RecomendationListViewModel

@Composable
fun MoreInfoView(movieId: String) {
    val recomendationListViewModel: RecomendationListViewModel = hiltViewModel()
    val recommendations by recomendationListViewModel.recommendations.collectAsState()
    val selectedMovie = recommendations.find { it.first?.id == movieId }


    Column {

        if (selectedMovie != null) {
            Log.d("moreinfo", "image: ${selectedMovie.first?.image}")
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()) {
                item {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Image(
                            painter = rememberAsyncImagePainter(model = selectedMovie.first?.image),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth() // Ocupa toda la línea horizontal
                                .height(225.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop,

                        )

                        Text(
                            text = "${selectedMovie.first?.title}",
                            style = MaterialTheme.typography.headlineSmall,
                            modifier = Modifier.padding(8.dp)
                        )

                        Text(text = "${selectedMovie.first?.originalTitle}")

                        Text(
                            text = "Lenguaje original",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier
                                .padding(8.dp)
                        )
                        Text(text = " ${selectedMovie.first?.language}")

                        Text(text = "Sinpsis", style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier
                                .padding(8.dp))
                        Text(text = " ${selectedMovie.first?.sinopsis}")

                        if (!selectedMovie.first?.opinion.isNullOrEmpty()) {
                            Text(
                                text = "Porque ver ${selectedMovie.first?.title}?",
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier
                                    .padding(8.dp)
                            )
                            Text(text = "Escucha la opinion de ${selectedMovie.second?.displayName}:")
                            Text(text = "${selectedMovie.first?.opinion}")
                        }
                        else {
                            Text(text = "recomendada por:",
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier
                                .padding(8.dp)
                            )

                            Text(text = "${selectedMovie.second?.displayName}")

                        }
                    }

                }
            }
        } else {
            Text(text = "Cargando...")
        }
    }
}