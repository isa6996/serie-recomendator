import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.serierecomendator.viewModel.SearchViewModel
import com.example.serierecomendator.data.model.retrofit.Result
import com.example.serierecomendator.data.model.retrofit.ResultMovies
import com.example.serierecomendator.data.model.retrofit.urlStringMovie

@Composable
fun SearchView(navController: NavHostController) {
    val searchVM: SearchViewModel = hiltViewModel()
    var searchedTitleMovie by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    var recommendationText by remember { mutableStateOf("") }
    var selectedMovie by remember { mutableStateOf<Result?>(null) } // Asegúrate de que Result esté definido

    val movies = searchVM.movies.observeAsState()

    var expanded by remember { mutableStateOf(false) }
    var mediaType by remember { mutableStateOf("tv") }
    val options = listOf("Series", "Películas", "Mangas", "Webtoons", "Novelas")
    var selectedOption by remember { mutableStateOf(options[0]) }


    Column {
        Text(text = "Recommendation")

        Row {
            Text(text = "Media Type:", modifier = Modifier.padding(10.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(Color(0xFF800080), RoundedCornerShape(16.dp))
                    .clip(RoundedCornerShape(16.dp)),
            ) {
                Text(
                    text = selectedOption,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { expanded = true }
                        .padding(16.dp),
                    textAlign = TextAlign.Center
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    options.forEach { option ->
                        DropdownMenuItem(text = { Text(text = option) }, onClick = {
                            selectedOption = option
                            mediaType = when (option) {
                                "Series / Anime" -> "tv"
                                "Películas" -> "movie"
                                "Man(g/h/hw)as" -> "manga"
                                "Webtoons" -> "webtoon"
                                "Novelas" -> "novel"
                                else -> "movie"
                            }
                            expanded = false
                        }
                        )
                    }
                }
            }
        }

        Row {
            TextField(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp)) // Redondea las esquinas en 16 dp
                    .weight(0.8f),
                value = searchedTitleMovie,
                onValueChange = { searchedTitleMovie = it },
                placeholder = { Text(searchedTitleMovie) })

            Button(onClick = { searchVM.TitleToSearch(searchedTitleMovie, mediaType) }) {
                Icon(Icons.Default.Search, contentDescription = "Search Icon")
            }
        }

        if (!movies.value.isNullOrEmpty()) {
            LazyColumn {

                movies.value!!.forEach { movie ->
                    try {
                    Log.d("MovieDBAPI", "valor: " + movie.name)
                    item {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            elevation = 4.dp
                        ) {

                            Row( // Use Row to arrange elements horizontally within the item
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Image(
                                    painter = rememberAsyncImagePainter(model = "${urlStringMovie}${movie.poster_path}"),
                                    contentDescription = "Movie poster for ${movie.name}",
                                    modifier = Modifier
                                        .fillMaxWidth(0.5f) // Occupy 50% of the width
                                        .height(200.dp)
                                        .clip(RoundedCornerShape(8.dp)),
                                )
                                Spacer(modifier = Modifier.width(8.dp)) // Add spacing between image and text
                                Column( // Use Column to stack text elements vertically
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Spacer(modifier = Modifier.height(15.dp))
                                    Text(
                                        text = movie.name,
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        style = MaterialTheme.typography.titleLarge
                                    )

                                    Text(
                                        text = movie.original_name,
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        style = MaterialTheme.typography.labelSmall
                                    )

                                    Spacer(modifier = Modifier.height(15.dp))


                                    Text(
                                        text = movie.first_air_date,
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                    Button(onClick = {
                                        selectedMovie = movie // Guardar el movie seleccionado
                                        showDialog = true
                                    }) {
                                        Text(text = "Recomendar")
                                    }
                                }

                            }
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                    } catch (e: Exception) {
                        Log.d("MovieDBAPI", "Error: " + e.message)
                        Log.d("MovieDBAPI", "Error: " + e.stackTrace)
                        Log.d("MovieDBAPI", "Error: " + e.cause)
                    }
                }
            }
        }else{
            Text(text = "No hay películas")
            Log.d("MovieDBAPI", "No hay pelis")
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = "Deja tu opinión?") },
            text = {
                TextField(
                    value = recommendationText,
                    onValueChange = { recommendationText = it },
                    label = { Text("Por que es un must be? (opcional)") },
                    modifier = Modifier.fillMaxWidth()
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        selectedMovie?.let { movie ->
                            searchVM.insertMovie(movie, recommendationText)
                        }
                        showDialog = false
                        recommendationText = "" // Reiniciar el texto
                    }
                ) {
                    Text("Enviar")
                }
            },
            dismissButton = {
                Button(onClick = { showDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
}
