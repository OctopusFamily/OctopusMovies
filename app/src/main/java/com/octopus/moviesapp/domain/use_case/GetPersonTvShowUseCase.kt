package com.octopus.moviesapp.domain.use_case

import com.octopus.moviesapp.data.repository.person.PersonRepository
import com.octopus.moviesapp.domain.model.TVShow
import javax.inject.Inject

class GetPersonTvShowUseCase @Inject constructor(
    private val personRepository: PersonRepository
) {

    suspend fun invoke(personId: Int): List<TVShow> {
        return personRepository.getPersonTVShowsById(personId)
    }
}