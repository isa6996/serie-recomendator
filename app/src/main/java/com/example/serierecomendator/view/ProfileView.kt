import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.serierecomendator.navigation.Screen
import com.example.serierecomendator.viewModel.ProfileViewModel

@Composable
fun ProfileView(navController: NavHostController) {
    Text(text = "Profile")
    var profileViewModel: ProfileViewModel = ProfileViewModel()


    Column {
        LazyColumn {
            item {
                Button(onClick = {
                    profileViewModel.LogOut()
                    navController.navigate(Screen.Login.route)
                }
                ) {

                }

            }
        }
    }
}