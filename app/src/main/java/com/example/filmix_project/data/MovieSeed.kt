package com.example.filmix_project.data

import com.example.filmix_project.R
import com.example.filmix_project.domain.Movie

object MovieSeed {
    val movies = listOf(
        Movie(
            id = 1,
            titleRu = "Интерстеллар",
            titleEn = "Interstellar",
            genre = "Sci-Fi,Drama,Adventure",
            year = 2014,
            rating = 8.6,
            descriptionRu = "Команда исследователей отправляется через червоточину в космос, чтобы найти новый дом для человечества.",
            descriptionEn = "A team of explorers travels through a wormhole in space to find a new home for humanity.",
            posterResource = R.drawable.poster_placeholder
        ),
        Movie(
            id = 2,
            titleRu = "Начало",
            titleEn = "Inception",
            genre = "Sci-Fi,Action,Thriller",
            year = 2010,
            rating = 8.8,
            descriptionRu = "Вор, который крадёт секреты из снов, получает задание внедрить идею в сознание человека.",
            descriptionEn = "A thief who steals secrets from dreams is given a task to plant an idea in a person's mind.",
            posterResource = R.drawable.poster_placeholder
        ),
        Movie(
            id = 3,
            titleRu = "Марсианин",
            titleEn = "The Martian",
            genre = "Drama,Sci-Fi,Adventure",
            year = 2015,
            rating = 8.0,
            descriptionRu = "Астронавт остаётся один на Марсе и должен выжить до прибытия спасательной миссии.",
            descriptionEn = "An astronaut is left alone on Mars and must survive until a rescue mission arrives.",
            posterResource = R.drawable.poster_placeholder
        ),
        Movie(
            id = 4,
            titleRu = "Гравитация",
            titleEn = "Gravity",
            genre = "Drama,Sci-Fi,Thriller",
            year = 2013,
            rating = 7.7,
            descriptionRu = "Двое астронавтов пытаются вернуться на Землю после аварии на орбите.",
            descriptionEn = "Two astronauts try to return to Earth after an accident in orbit.",
            posterResource = R.drawable.poster_placeholder
        ),
        Movie(
            id = 5,
            titleRu = "Дюна",
            titleEn = "Dune",
            genre = "Sci-Fi,Action",
            year = 2021,
            rating = 8.0,
            descriptionRu = "Наследник знатного рода отправляется на опасную пустынную планету, где добывают самый ценный ресурс во вселенной.",
            descriptionEn = "The heir of a noble house travels to a dangerous desert planet that produces the most valuable resource in the universe.",
            posterResource = R.drawable.poster_placeholder
        ),
        Movie(
            id = 6,
            titleRu = "Прибытие",
            titleEn = "Arrival",
            genre = "Sci-Fi,Drama,Mystery",
            year = 2016,
            rating = 7.9,
            descriptionRu = "Лингвистка пытается наладить контакт с пришельцами, прибывшими на Землю.",
            descriptionEn = "A linguist tries to establish contact with aliens who have arrived on Earth.",
            posterResource = R.drawable.poster_placeholder
        )
    )
}
