package com.octopus.moviesapp.android.usecases.person

import com.octopus.moviesapp.android.usecases.mapper.MoviesMapper
import com.octopus.moviesapp.models.model.Movie
import com.octopus.moviesapp.repositories.repository.person.PersonRepository
import javax.inject.Inject

class GetPersonMoviesUseCase @Inject constructor(
    private val moviesMapper: MoviesMapper,
    private val personRepository: PersonRepository
) {

    suspend operator fun invoke(personId: Int): List<Movie> {
        return moviesMapper.mapList(personRepository.getPersonMoviesById(personId))
    }
}