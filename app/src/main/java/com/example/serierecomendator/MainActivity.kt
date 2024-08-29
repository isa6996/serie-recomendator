package com.example.serierecomendator

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.serierecomendator.navigation.BottomNavigationBar
import com.example.serierecomendator.navigation.Navigation
import com.example.serierecomendator.navigation.Screen
//import com.example.serierecomendator.data.RetrofitServiceFactory
import com.example.serierecomendator.ui.theme.SerieRecomendatorTheme
import com.example.serierecomendator.view.LoginView
import com.example.serierecomendator.view.SignUpView
import com.example.serierecomendator.viewModel.LoginViewModel
import com.example.serierecomendator.viewModel.SignUpViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

   /*     val service = RetrofitServiceFactory.makeRetrofitService()
        lifecycleScope.launch{
            val movies =service.listSeries("")
            println(movies)
        }*/

        setContent {
            MainScreen()

        }

    }

}

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
                BottomNavigationBar(navController)
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Navigation(navController = navController)
        }
    }
}


