package com.example.filmix_project

import android.content.Intent
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.filmix_project.data.MovieRepository
import com.example.filmix_project.ui.components.BottomTab
import com.example.filmix_project.ui.components.FilmixBottomBar
import com.example.filmix_project.ui.openMovie
import com.example.filmix_project.ui.screens.HomeScreen
import com.example.filmix_project.ui.screens.SearchScreen
import com.example.filmix_project.ui.screens.SettingsScreen

@Composable
fun AppRoot(
    requestedTab: BottomTab?,
    onTabHandled: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val repository = remember { MovieRepository(context) }
    val movies = remember { repository.getAll() }

    var tab by rememberSaveable { mutableStateOf(BottomTab.Home) }
    var searching by rememberSaveable { mutableStateOf(false) }
    var query by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(requestedTab) {
        if (requestedTab != null) {
            tab = requestedTab
            searching = false
            query = ""
            onTabHandled()
        }
    }

    BackHandler(enabled = searching) {
        searching = false
        query = ""
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            FilmixBottomBar(
                selected = tab,
                onSelect = { selected ->
                    if (selected == BottomTab.Favorites) {
                        context.startActivity(Intent(context, FavoritesActivity::class.java))
                    } else {
                        tab = selected
                        searching = false
                        query = ""
                    }
                }
            )
        }
    ) { innerPadding ->
        val contentModifier = Modifier.padding(innerPadding)

        when {
            tab == BottomTab.Settings -> {
                SettingsScreen(modifier = contentModifier)
            }

            searching -> {
                SearchScreen(
                    query = query,
                    onQueryChange = { query = it },
                    results = repository.search(query),
                    onMovieClick = { movie -> openMovie(context, movie.id) },
                    modifier = contentModifier
                )
            }

            else -> {
                HomeScreen(
                    movies = movies,
                    onSearchClick = { searching = true },
                    onMovieClick = { movie -> openMovie(context, movie.id) },
                    modifier = contentModifier
                )
            }
        }
    }
}
