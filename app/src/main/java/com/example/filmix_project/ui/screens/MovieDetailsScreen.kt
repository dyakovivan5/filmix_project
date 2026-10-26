package com.example.filmix_project.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.filmix_project.R
import com.example.filmix_project.data.MovieRepository
import com.example.filmix_project.domain.Recommender
import com.example.filmix_project.ui.components.MoviePoster
import com.example.filmix_project.ui.components.MoviesGrid
import com.example.filmix_project.ui.components.SectionTitle
import com.example.filmix_project.ui.currentLanguage
import com.example.filmix_project.ui.genreName
import com.example.filmix_project.ui.openMovie

@Composable
fun MovieDetailsScreen(
    movieId: Int,
    refreshKey: Int,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val repository = remember { MovieRepository(context) }
    val language = currentLanguage()

    val movie = remember(movieId) { repository.getById(movieId) }
    if (movie == null) {
        onBack()
        return
    }

    var isFavorite by remember(refreshKey) { mutableStateOf(repository.isFavorite(movieId)) }
    val similar = remember(movieId) { Recommender.similar(movie, repository.getAll()) }
    val title = movie.title(language)

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = stringResource(R.string.back)
                )
            }
            Text(
                text = stringResource(R.string.movie_title),
                style = MaterialTheme.typography.titleLarge
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            MoviePoster(
                movie = movie,
                title = title,
                modifier = Modifier
                    .width(110.dp)
                    .aspectRatio(0.68f),
                titleSize = 14.sp
            ) {
                Text(
                    text = movie.mainGenre.uppercase(),
                    color = Color.White,
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(8.dp)
                        .fillMaxWidth()
                        .background(Color(0xCC0F0F0F), RoundedCornerShape(6.dp))
                        .padding(vertical = 4.dp)
                )
            }

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "${movie.year} • ${genreName(movie.mainGenre)}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "★ ${movie.rating} / 10",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { isFavorite = repository.toggleFavorite(movie.id) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp),
                    shape = RoundedCornerShape(8.dp),
                ) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = stringResource(
                            if (isFavorite) R.string.in_favorites else R.string.add_to_favorites
                        ),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        SectionTitle(text = stringResource(R.string.description))
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = movie.description(language),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        if (similar.isNotEmpty()) {
            Spacer(modifier = Modifier.height(24.dp))
            SectionTitle(text = stringResource(R.string.similar_movies))
            Spacer(modifier = Modifier.height(12.dp))
            MoviesGrid(
                movies = similar,
                onMovieClick = { other -> openMovie(context, other.id) }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}
