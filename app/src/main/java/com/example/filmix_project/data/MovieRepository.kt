package com.example.filmix_project.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.example.filmix_project.domain.Movie

class MovieRepository(context: Context) {

    private val helper = MovieDbHelper(context.applicationContext)

    fun getAll(): List<Movie> {
        return queryMovies(null, null)
    }

    fun getById(id: Int): Movie? {
        return queryMovies("${MovieDbHelper.COLUMN_ID} = ?", arrayOf(id.toString())).firstOrNull()
    }

    fun search(query: String): List<Movie> {
        val text = query.trim()
        if (text.isEmpty()) return getAll()

        return getAll().filter {
            it.titleRu.contains(text, ignoreCase = true) ||
                    it.titleEn.contains(text, ignoreCase = true)
        }
    }

    fun getFavorites(): List<Movie> {
        return queryMovies(
            "${MovieDbHelper.COLUMN_ID} IN (SELECT ${MovieDbHelper.COLUMN_MOVIE_ID} FROM ${MovieDbHelper.TABLE_FAVORITES})",
            null
        )
    }

    fun isFavorite(movieId: Int): Boolean {
        val cursor = helper.readableDatabase.query(
            MovieDbHelper.TABLE_FAVORITES,
            null,
            "${MovieDbHelper.COLUMN_MOVIE_ID} = ?",
            arrayOf(movieId.toString()),
            null,
            null,
            null
        )
        return cursor.use { it.count > 0 }
    }

    fun toggleFavorite(movieId: Int): Boolean {
        val db = helper.writableDatabase

        return if (isFavorite(movieId)) {
            db.delete(
                MovieDbHelper.TABLE_FAVORITES,
                "${MovieDbHelper.COLUMN_MOVIE_ID} = ?",
                arrayOf(movieId.toString())
            )
            false
        } else {
            val values = ContentValues().apply {
                put(MovieDbHelper.COLUMN_MOVIE_ID, movieId)
            }
            db.insert(MovieDbHelper.TABLE_FAVORITES, null, values)
            true
        }
    }

    private fun queryMovies(selection: String?, selectionArgs: Array<String>?): List<Movie> {
        val movies = mutableListOf<Movie>()
        val cursor = helper.readableDatabase.query(
            MovieDbHelper.TABLE_MOVIES,
            null,
            selection,
            selectionArgs,
            null,
            null,
            "${MovieDbHelper.COLUMN_ID} ASC"
        )

        cursor.use {
            while (it.moveToNext()) {
                movies.add(readMovie(it))
            }
        }
        return movies
    }

    private fun readMovie(cursor: Cursor): Movie {
        return Movie(
            id = cursor.getInt(cursor.getColumnIndexOrThrow(MovieDbHelper.COLUMN_ID)),
            titleRu = cursor.getString(cursor.getColumnIndexOrThrow(MovieDbHelper.COLUMN_TITLE_RU)),
            titleEn = cursor.getString(cursor.getColumnIndexOrThrow(MovieDbHelper.COLUMN_TITLE_EN)),
            genre = cursor.getString(cursor.getColumnIndexOrThrow(MovieDbHelper.COLUMN_GENRE)),
            year = cursor.getInt(cursor.getColumnIndexOrThrow(MovieDbHelper.COLUMN_YEAR)),
            rating = cursor.getDouble(cursor.getColumnIndexOrThrow(MovieDbHelper.COLUMN_RATING)),
            descriptionRu = cursor.getString(cursor.getColumnIndexOrThrow(MovieDbHelper.COLUMN_DESCRIPTION_RU)),
            descriptionEn = cursor.getString(cursor.getColumnIndexOrThrow(MovieDbHelper.COLUMN_DESCRIPTION_EN)),
            posterResource = cursor.getInt(cursor.getColumnIndexOrThrow(MovieDbHelper.COLUMN_POSTER))
        )
    }
}
