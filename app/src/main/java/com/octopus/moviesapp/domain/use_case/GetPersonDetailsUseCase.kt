package com.octopus.moviesapp.domain.use_case

import com.octopus.moviesapp.data.repository.person.PersonRepository
import com.octopus.moviesapp.domain.model.PersonDetails
import javax.inject.Inject

class GetPersonDetailsUseCase @Inject constructor(
    private val personRepository: PersonRepository
) {
    suspend fun invoke(personId: Int): PersonDetails {
        return personRepository.getPersonDetailsById(personId)
    }
}