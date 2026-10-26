package com.example.filmix_project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.filmix_project.ui.screens.MovieDetailsScreen
import com.example.filmix_project.ui.theme.Filmix_projectTheme

class MovieDetailsActivity : ComponentActivity() {

    private var refreshKey by mutableIntStateOf(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val movieId = intent.getIntExtra(EXTRA_MOVIE_ID, -1)

        setContent {
            Filmix_projectTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) { innerPadding ->
                    MovieDetailsScreen(
                        movieId = movieId,
                        refreshKey = refreshKey,
                        onBack = { finish() },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        refreshKey++
    }

    companion object {
        const val EXTRA_MOVIE_ID = "movie_id"
    }
}
