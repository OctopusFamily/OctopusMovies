package com.octopus.moviesapp.data.repository.person

import com.octopus.moviesapp.data.remote.service.TMDBApiService
import com.octopus.moviesapp.domain.mapper.MoviesMapper
import com.octopus.moviesapp.domain.mapper.PersonDetailsMapper
import com.octopus.moviesapp.domain.mapper.TVShowsMapper
import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.domain.model.PersonDetails
import com.octopus.moviesapp.domain.model.TVShow
import javax.inject.Inject

class PersonRepositoryImpl @Inject constructor(
    private val tmdbApiService: TMDBApiService,
    private val personDetailsMapper: PersonDetailsMapper,
    private val moviesMapper: MoviesMapper,
    private val tvShowsMapper: TVShowsMapper,
) : PersonRepository {
    override suspend fun getPersonDetailsById(personId: Int): PersonDetails {
        return personDetailsMapper.map(tmdbApiService.getPersonDetailsById(personId))
    }

    override suspend fun getPersonMoviesById(personId: Int): List<Movie> {
        return moviesMapper.map(tmdbApiService.getPersonMoviesById(personId).items)
    }

    override suspend fun getPersonTVShowsById(personId: Int): List<TVShow> {
        return tvShowsMapper.mapList(tmdbApiService.getPersonTVShowsById(personId).items)
    }
}