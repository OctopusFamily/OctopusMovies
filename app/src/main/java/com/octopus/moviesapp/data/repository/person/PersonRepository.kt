package com.octopus.moviesapp.data.repository.person

import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.domain.model.PersonDetails
import com.octopus.moviesapp.domain.model.TVShow

interface PersonRepository {
    suspend fun getPersonDetailsById(personId: Int): PersonDetails
    suspend fun getPersonMoviesById(personId: Int): List<Movie>
    suspend fun getPersonTVShowsById(personId: Int): List<TVShow>
}