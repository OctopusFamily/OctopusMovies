package com.octopus.moviesapp.android.usecases

import com.octopus.moviesapp.android.usecases.mapper.TVShowsMapper
import com.octopus.moviesapp.models.model.TVShow
import com.octopus.moviesapp.repositories.repository.person.PersonRepository
import javax.inject.Inject

class GetPersonTVShowsUseCase @Inject constructor(
    private val tvShowsMapper: TVShowsMapper,
    private val personRepository: PersonRepository
) {

    suspend operator fun invoke(personId: Int): List<TVShow> {
        return tvShowsMapper.mapList(personRepository.getPersonTVShowsById(personId))
    }
}