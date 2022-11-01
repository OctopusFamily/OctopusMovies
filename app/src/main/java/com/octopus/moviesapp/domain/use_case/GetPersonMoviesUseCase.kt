package com.octopus.moviesapp.domain.use_case

import com.octopus.moviesapp.data.repository.person.PersonRepository
import com.octopus.moviesapp.domain.mapper.MoviesMapper
import com.octopus.moviesapp.domain.model.Movie
import javax.inject.Inject

class GetPersonMoviesUseCase @Inject constructor(
    private val moviesMapper: MoviesMapper,
    private val personRepository: PersonRepository
) {

    suspend operator fun invoke(personId: Int): List<Movie> {
        return moviesMapper.mapList(personRepository.getPersonMoviesById(personId))
    }
}