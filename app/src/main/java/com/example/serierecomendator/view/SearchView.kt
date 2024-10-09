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
import androidx.compose.material.Card
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.serierecomendator.viewModel.SearchViewModel

@Composable
fun SearchView(navController: NavHostController) {
    val searchVM: SearchViewModel = hiltViewModel()
    var searchedTitleMovie by remember { mutableStateOf("") }

    val movies = searchVM.movies.observeAsState()


    Column {
        Text(text = "Recommendation")

        Row {
            TextField(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp)) // Redondea las esquinas en 16 dp
                    .weight(0.8f),
                value = searchedTitleMovie,
                onValueChange = { searchedTitleMovie = it },
                placeholder = { Text(searchedTitleMovie) })

            Button(onClick = { searchVM.TitleToSearch(searchedTitleMovie) }) {
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
                                    painter = rememberAsyncImagePainter(model = "${searchVM.urlStringMovie}${movie.poster_path}"),
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
                                    Button(onClick = { searchVM.insertMovie(movie) }) {
                                        Text(text = "recomendar")
                                    }
                                    // Text(text = movie.overview)
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
}
