package com.example.serierecomendator.view

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.TextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.serierecomendator.viewModel.RecomendationListViewModel

@Composable
fun RecomendationListView(navController: NavHostController,
                          recomendationVM: RecomendationListViewModel = hiltViewModel()) {

    val recomendedMovies by recomendationVM.recommendedMovies.collectAsState()

Log.d("RecomendationListView", recomendedMovies.size.toString())
    Text(text = "RecomendationListView")

    Column(modifier = Modifier.padding(16.dp)) {
        if (recomendedMovies.isNotEmpty()) {
            LazyColumn {
                items(recomendedMovies.size) { index ->
                    recomendedMovies[index]?.let { movie ->
                        Text(text = movie.title)
                        Log.d("RecomendationListView", "olii ${movie.title}")
                    }
                }
            }
        } else {
            Log.d("RecomendationListView", "algo no va bien...")
            Text(text = "No recommendations available.")
        }

    }
}
