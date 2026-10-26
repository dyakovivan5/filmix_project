package com.example.filmix_project.domain

object Recommender {

    fun similar(movie: Movie, all: List<Movie>, limit: Int = 3): List<Movie> {
        val genres = movie.genres.toSet()

        return all
            .filter { it.id != movie.id }
            .map { other -> other to other.genres.count { it in genres } }
            .filter { it.second > 0 }
            .sortedByDescending { it.second }
            .take(limit)
            .map { it.first }
    }

    fun forFavorites(favorites: List<Movie>, all: List<Movie>, limit: Int = 3): List<Movie> {
        val favoriteIds = favorites.map { it.id }.toSet()
        val weights = HashMap<String, Int>()

        for (movie in favorites) {
            for (genre in movie.genres) {
                weights[genre] = (weights[genre] ?: 0) + 1
            }
        }

        return all
            .filter { it.id !in favoriteIds }
            .map { movie -> movie to movie.genres.sumOf { weights[it] ?: 0 } }
            .filter { it.second > 0 }
            .sortedByDescending { it.second }
            .take(limit)
            .map { it.first }
    }
}
