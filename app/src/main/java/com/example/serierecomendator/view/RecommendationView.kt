import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.serierecomendator.R
import com.example.serierecomendator.viewModel.RecommendationViewModel

@Composable
fun RecommendationView(navController: NavHostController) {
    val recommendationViewModel: RecommendationViewModel = hiltViewModel()
    Text(text = "Recommendation")
    Log.d("MovieDBAPI2", "valor : " + recommendationViewModel.movies.value?.get(0)?.name)

    val movies = recommendationViewModel.movies.observeAsState()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = 4.dp
    ) {
        Column {
            LazyColumn {
                if (movies.value != null) {
                    item {
                        Text(
                            text = movies.value!![0].overview,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    // Acción al hacer clic en la primera película
                                }
                                .padding(8.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = movies.value!![1].overview,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    // Acción al hacer clic en la segunda película
                                }
                                .padding(8.dp)
                        )
                    }
                } else {
                    item {
                        Text(text = "No hay películas")
                    }
                }
            }
        }
    }
}