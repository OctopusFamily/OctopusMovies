package com.octopus.moviesapp.data.repository

import com.octopus.moviesapp.domain.model.Cast
import com.octopus.moviesapp.domain.model.PersonDetails

interface PersonRepository {
    suspend fun getPersonDetailsById(personId: Int): PersonDetails
    suspend fun getPersonMoviesById(personId: Int): List<Cast>
    suspend fun getPersonTVShowsById(personId: Int): List<Cast>
}