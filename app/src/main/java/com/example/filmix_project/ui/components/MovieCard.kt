package com.example.filmix_project.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.filmix_project.domain.Movie
import com.example.filmix_project.ui.currentLanguage
import com.example.filmix_project.ui.genreName

@Composable
fun MovieCard(
    movie: Movie,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val title = movie.title(currentLanguage())

    Column(modifier = modifier.clickable(onClick = onClick)) {
        MoviePoster(
            movie = movie,
            title = title,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(0.7f)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = "${movie.year} • ${genreName(movie.mainGenre)}",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = "★ ${movie.rating}",
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
fun MoviesGrid(
    movies: List<Movie>,
    onMovieClick: (Movie) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        for (rowMovies in movies.chunked(COLUMNS)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                for (movie in rowMovies) {
                    MovieCard(
                        movie = movie,
                        onClick = { onMovieClick(movie) },
                        modifier = Modifier.weight(1f)
                    )
                }
                repeat(COLUMNS - rowMovies.size) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

private const val COLUMNS = 3
