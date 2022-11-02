package com.octopus.moviesapp.data.repository.person

import com.octopus.moviesapp.data.remote.response.dto.MovieDTO
import com.octopus.moviesapp.data.remote.response.dto.PersonDTO
import com.octopus.moviesapp.data.remote.response.dto.TVShowDTO

interface PersonRepository {
    suspend fun getPersonDetailsById(personId: Int): PersonDTO
    suspend fun getPersonMoviesById(personId: Int): List<MovieDTO>
    suspend fun getPersonTVShowsById(personId: Int): List<TVShowDTO>
}