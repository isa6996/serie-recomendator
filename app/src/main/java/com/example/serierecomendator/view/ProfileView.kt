import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.serierecomendator.viewModel.ProfileViewModel


@Composable
fun ProfileView(navController: NavHostController) {

    val profileViewModel: ProfileViewModel = ProfileViewModel()
    val userName by profileViewModel.userName.collectAsState()
    val userIma by profileViewModel.userIma.collectAsState()

    LaunchedEffect(Unit) {
        profileViewModel.userInformation()
    }


    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Profile")
        LazyColumn (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            item {
                Text(text = userName,
                    modifier = Modifier.padding(8.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))
                AsyncImage(
                    model = userIma,
                    contentDescription = null,
                    modifier = Modifier.size(200.dp)
                    )
                Log.d("image", "image: " + userIma)
                Spacer(modifier = Modifier.height(20.dp))
            }


        }
    }
}