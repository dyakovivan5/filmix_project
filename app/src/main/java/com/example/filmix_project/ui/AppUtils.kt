package com.example.filmix_project.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import com.example.filmix_project.R

@Composable
fun currentLanguage(): String {
    return LocalConfiguration.current.locales[0].language
}

@Composable
fun genreName(genre: String): String {
    val id = when (genre) {
        "Sci-Fi" -> R.string.genre_sci_fi
        "Drama" -> R.string.genre_drama
        "Adventure" -> R.string.genre_adventure
        "Action" -> R.string.genre_action
        "Thriller" -> R.string.genre_thriller
        "Mystery" -> R.string.genre_mystery
        else -> null
    }
    return if (id != null) stringResource(id) else genre
}
