package com.example.filmix_project.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MovieDbHelper(context: Context) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    override fun onConfigure(db: SQLiteDatabase) {
        super.onConfigure(db)
        db.setForeignKeyConstraintsEnabled(true)
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            """
            CREATE TABLE $TABLE_MOVIES (
                $COLUMN_ID INTEGER PRIMARY KEY,
                $COLUMN_TITLE_RU TEXT NOT NULL,
                $COLUMN_TITLE_EN TEXT NOT NULL,
                $COLUMN_GENRE TEXT NOT NULL,
                $COLUMN_YEAR INTEGER NOT NULL,
                $COLUMN_RATING REAL NOT NULL,
                $COLUMN_DESCRIPTION_RU TEXT NOT NULL,
                $COLUMN_DESCRIPTION_EN TEXT NOT NULL,
                $COLUMN_POSTER INTEGER NOT NULL
            )
            """.trimIndent()
        )

        db.execSQL(
            """
            CREATE TABLE $TABLE_FAVORITES (
                $COLUMN_MOVIE_ID INTEGER PRIMARY KEY,
                FOREIGN KEY ($COLUMN_MOVIE_ID) REFERENCES $TABLE_MOVIES($COLUMN_ID) ON DELETE CASCADE
            )
            """.trimIndent()
        )

        for (movie in MovieSeed.movies) {
            val values = ContentValues().apply {
                put(COLUMN_ID, movie.id)
                put(COLUMN_TITLE_RU, movie.titleRu)
                put(COLUMN_TITLE_EN, movie.titleEn)
                put(COLUMN_GENRE, movie.genre)
                put(COLUMN_YEAR, movie.year)
                put(COLUMN_RATING, movie.rating)
                put(COLUMN_DESCRIPTION_RU, movie.descriptionRu)
                put(COLUMN_DESCRIPTION_EN, movie.descriptionEn)
                put(COLUMN_POSTER, movie.posterResource)
            }
            db.insert(TABLE_MOVIES, null, values)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_FAVORITES")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_MOVIES")
        onCreate(db)
    }

    companion object {
        private const val DB_NAME = "filmix.db"
        private const val DB_VERSION = 1

        const val TABLE_MOVIES = "movies"
        const val TABLE_FAVORITES = "favorites"

        const val COLUMN_ID = "id"
        const val COLUMN_TITLE_RU = "title_ru"
        const val COLUMN_TITLE_EN = "title_en"
        const val COLUMN_GENRE = "genre"
        const val COLUMN_YEAR = "year"
        const val COLUMN_RATING = "rating"
        const val COLUMN_DESCRIPTION_RU = "description_ru"
        const val COLUMN_DESCRIPTION_EN = "description_en"
        const val COLUMN_POSTER = "poster_resource"
        const val COLUMN_MOVIE_ID = "movie_id"
    }
}
