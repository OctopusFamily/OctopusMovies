package com.octopus.moviesapp.android.usecases.use_case

import com.octopus.moviesapp.android.usecases.mapper.PersonDetailsMapper
import com.octopus.moviesapp.models.model.PersonDetails
import com.octopus.moviesapp.repositories.repository.person.PersonRepository
import javax.inject.Inject

class GetPersonDetailsUseCase @Inject constructor(
    private val personDetailsMapper: PersonDetailsMapper,
    private val personRepository: PersonRepository
) {
    suspend operator fun invoke(personId: Int): PersonDetails {
        return personDetailsMapper.map(personRepository.getPersonDetailsById(personId))
    }
}