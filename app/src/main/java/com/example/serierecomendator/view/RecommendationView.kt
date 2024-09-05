import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.serierecomendator.viewModel.RecommendationViewModel

@Composable
fun RecommendationView(navHostController: NavHostController) {
    val recommendationViewModel : RecommendationViewModel= hiltViewModel()
    Text(text = "Recommendation")
}