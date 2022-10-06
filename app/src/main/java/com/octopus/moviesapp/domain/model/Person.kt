package com.octopus.moviesapp.domain.model

data class Person(
    val personImageUrl: String,
    val personName: String,
    val biography: String,
    val birthday: String,
    val career: String,
    val popularity: Float,
    val placeOfBirth: String,
)

