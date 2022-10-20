package com.octopus.moviesapp.domain.model

import java.util.Date

data class PersonDetails(
    val name: String,
    val profilePath: String,
    val biography: String,
    val birthday: Date,
    val knownForDepartment: String,
    val popularity: Float,
    val placeOfBirth: String
)
