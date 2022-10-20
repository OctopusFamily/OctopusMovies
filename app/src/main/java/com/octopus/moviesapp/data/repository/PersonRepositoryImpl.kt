package com.octopus.moviesapp.data.repository

import com.octopus.moviesapp.data.remote.service.TMDBApiService
import com.octopus.moviesapp.domain.mapper.CastMapper
import com.octopus.moviesapp.domain.mapper.PersonDetailsMapper
import com.octopus.moviesapp.domain.model.Cast
import com.octopus.moviesapp.domain.model.PersonDetails
import javax.inject.Inject

class PersonRepositoryImpl @Inject constructor(
    private val tmdbApiService: TMDBApiService,
    private val personDetailsMapper: PersonDetailsMapper,
    private val castMapper: CastMapper
) : PersonRepository {
    override suspend fun getPersonDetailsById(personId: Int): PersonDetails {
        return personDetailsMapper.map(tmdbApiService.getPersonDetailsById(personId))
    }

    override suspend fun getPersonMoviesById(personId: Int): List<Cast> {
        return castMapper.map(tmdbApiService.getPersonMoviesById(personId).itemsList)
    }

    override suspend fun getPersonTVShowsById(personId: Int): List<Cast> {
        return castMapper.map(tmdbApiService.getPersonTVShowsById(personId).itemsList)
    }
}