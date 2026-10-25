package com.example.filmix_project.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.filmix_project.R
import com.example.filmix_project.data.MovieRepository
import com.example.filmix_project.domain.Recommender
import com.example.filmix_project.ui.components.MoviesGrid
import com.example.filmix_project.ui.components.ScreenTitle
import com.example.filmix_project.ui.components.SectionTitle
import com.example.filmix_project.ui.openMovie

@Composable
fun FavoritesScreen(
    refreshKey: Int,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val repository = remember { MovieRepository(context) }

    val favorites = remember(refreshKey) { repository.getFavorites() }
    val recommendations = remember(refreshKey) {
        Recommender.forFavorites(favorites, repository.getAll())
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        ScreenTitle(text = stringResource(R.string.favorites_title))
        Text(
            text = stringResource(R.string.favorites_subtitle),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(16.dp))
        if (favorites.isEmpty()) {
            Text(
                text = stringResource(R.string.favorites_empty),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        } else {
            MoviesGrid(
                movies = favorites,
                onMovieClick = { movie -> openMovie(context, movie.id) }
            )
        }

        if (recommendations.isNotEmpty()) {
            Spacer(modifier = Modifier.height(24.dp))
            SectionTitle(text = stringResource(R.string.for_you))

            Spacer(modifier = Modifier.height(12.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .padding(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.based_on_favorites),
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = stringResource(R.string.you_may_like),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
            MoviesGrid(
                movies = recommendations,
                onMovieClick = { movie -> openMovie(context, movie.id) }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}
