package com.example.filmix_project.domain

data class Movie(
    val id: Int,
    val titleRu: String,
    val titleEn: String,
    val genre: String,
    val year: Int,
    val rating: Double,
    val descriptionRu: String,
    val descriptionEn: String,
    val posterResource: Int
) {
    val genres: List<String>
        get() = genre.split(",")

    val mainGenre: String
        get() = genres.first()

    fun title(language: String): String {
        return if (language == "en") titleEn else titleRu
    }

    fun description(language: String): String {
        return if (language == "en") descriptionEn else descriptionRu
    }
}
