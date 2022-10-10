package com.octopus.moviesapp.domain.model

import com.octopus.moviesapp.domain.enums.GenresList

data class Genre(
    val id: Int,
    val name: String,
    val type: GenresList,
)
