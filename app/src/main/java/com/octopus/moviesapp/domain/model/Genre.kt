package com.octopus.moviesapp.domain.model

import com.octopus.moviesapp.android.local.types.GenresType

data class Genre(
    val id: Int,
    val name: String,
    val type: GenresType,
)
