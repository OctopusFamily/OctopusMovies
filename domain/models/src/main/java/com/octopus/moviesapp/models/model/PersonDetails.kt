package com.octopus.moviesapp.models.model

data class PersonDetails(
    val name: String,
    val profilePath: String,
    val biography: String,
    val birthday: String,
    val knownForDepartment: String,
    val popularity: Float,
    val placeOfBirth: String
)
