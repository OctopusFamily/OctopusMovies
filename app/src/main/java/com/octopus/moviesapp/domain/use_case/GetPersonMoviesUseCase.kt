package com.octopus.moviesapp.domain.use_case

import com.octopus.moviesapp.data.repository.person.PersonRepository
import com.octopus.moviesapp.domain.model.Movie
import javax.inject.Inject

class GetPersonMoviesUseCase @Inject constructor(
    private val personRepository: PersonRepository
) {

    suspend fun invoke(personId: Int): List<Movie> {
        return personRepository.getPersonMoviesById(personId)
    }
}