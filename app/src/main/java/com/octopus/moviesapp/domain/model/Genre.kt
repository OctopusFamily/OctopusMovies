package com.octopus.moviesapp.domain.model

data class Genre(
    val id: Int,
    val name: String,
    val type: List<Genre>,
)
