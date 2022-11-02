package com.octopus.moviesapp.domain.use_case

import com.octopus.moviesapp.data.repository.person.PersonRepository
import com.octopus.moviesapp.domain.mapper.TVShowsMapper
import com.octopus.moviesapp.domain.model.TVShow
import javax.inject.Inject

class GetPersonTVShowsUseCase @Inject constructor(
    private val tvShowsMapper: TVShowsMapper,
    private val personRepository: PersonRepository
) {

    suspend operator fun invoke(personId: Int): List<TVShow> {
        return tvShowsMapper.mapList(personRepository.getPersonTVShowsById(personId))
    }
}