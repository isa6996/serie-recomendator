import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import com.example.serierecomendator.navigation.Screen
import com.example.serierecomendator.viewModel.ProfileViewModel

@Composable
fun ProfileView(navController: NavHostController) {
    Text(text = "Profile")
    val profileViewModel: ProfileViewModel = ProfileViewModel()
    val userName by profileViewModel.userName.collectAsState()
    val userIma by profileViewModel.userIma.collectAsState()


    Column {
        LazyColumn {
            item {
                Text(text = userName)

            }


        }
    }
}