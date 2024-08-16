package com.example.serierecomendator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
//import com.example.serierecomendator.data.RetrofitServiceFactory
import com.example.serierecomendator.ui.theme.SerieRecomendatorTheme
import com.example.serierecomendator.view.SignUpView
import com.example.serierecomendator.viewModel.SignUpViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

   /*     val service = RetrofitServiceFactory.makeRetrofitService()
        lifecycleScope.launch{
            val movies =service.listSeries("e32dd4598e7cb2f7dc42b4bf44d971c3","US")
            println(movies)
        }*/

        setContent {
            SignUpView()
        }

    }

}
