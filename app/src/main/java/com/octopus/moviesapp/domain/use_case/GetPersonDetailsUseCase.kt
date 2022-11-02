package com.octopus.moviesapp.domain.use_case

import com.octopus.moviesapp.data.repository.person.PersonRepository
import com.octopus.moviesapp.domain.mapper.PersonDetailsMapper
import com.octopus.moviesapp.domain.model.PersonDetails
import javax.inject.Inject

class GetPersonDetailsUseCase @Inject constructor(
    private val personDetailsMapper: PersonDetailsMapper,
    private val personRepository: PersonRepository
) {
    suspend operator fun invoke(personId: Int): PersonDetails {
        return personDetailsMapper.map(personRepository.getPersonDetailsById(personId))
    }
}