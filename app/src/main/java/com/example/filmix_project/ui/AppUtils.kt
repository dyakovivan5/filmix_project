package com.example.filmix_project.ui

import android.app.LocaleManager
import android.content.Context
import android.content.Intent
import android.os.LocaleList
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import com.example.filmix_project.MainActivity
import com.example.filmix_project.MovieDetailsActivity
import com.example.filmix_project.R
import com.example.filmix_project.ui.components.BottomTab

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

fun changeLanguage(context: Context, language: String) {
    val manager = context.getSystemService(LocaleManager::class.java)
    manager.applicationLocales = LocaleList.forLanguageTags(language)
}

fun openMovie(context: Context, movieId: Int) {
    val intent = Intent(context, MovieDetailsActivity::class.java)
    intent.putExtra(MovieDetailsActivity.EXTRA_MOVIE_ID, movieId)
    context.startActivity(intent)
}

fun openMain(context: Context, tab: BottomTab) {
    val intent = Intent(context, MainActivity::class.java)
    intent.putExtra(MainActivity.EXTRA_TAB, tab.name)
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
    context.startActivity(intent)
}
