package com.octopus.moviesapp.data.repository.person

import com.octopus.moviesapp.data.remote.response.dto.MovieDTO
import com.octopus.moviesapp.data.remote.response.dto.PersonDTO
import com.octopus.moviesapp.data.remote.response.dto.TVShowDTO
import com.octopus.moviesapp.data.remote.service.TMDBApiService
import javax.inject.Inject

class PersonRepositoryImpl @Inject constructor(
    private val tmdbApiService: TMDBApiService,

    ) : PersonRepository {
    override suspend fun getPersonDetailsById(personId: Int): PersonDTO {
        return tmdbApiService.getPersonDetailsById(personId)
    }

    override suspend fun getPersonMoviesById(personId: Int): List<MovieDTO> {
        return tmdbApiService.getPersonMoviesById(personId).items
    }

    override suspend fun getPersonTVShowsById(personId: Int): List<TVShowDTO> {
        return tmdbApiService.getPersonTVShowsById(personId).items
    }
}