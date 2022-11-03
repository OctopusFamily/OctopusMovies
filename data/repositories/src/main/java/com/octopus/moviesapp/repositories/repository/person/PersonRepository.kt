package com.octopus.moviesapp.repositories.repository.person

import com.octopus.moviesapp.android.remote.response.dto.MovieDTO
import com.octopus.moviesapp.android.remote.response.dto.PersonDTO
import com.octopus.moviesapp.android.remote.response.dto.TVShowDTO

interface PersonRepository {
    suspend fun getPersonDetailsById(personId: Int): PersonDTO
    suspend fun getPersonMoviesById(personId: Int): List<MovieDTO>
    suspend fun getPersonTVShowsById(personId: Int): List<TVShowDTO>
}