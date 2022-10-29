package com.octopus.moviesapp.domain.use_case.moviedetails_usecase

import com.octopus.moviesapp.data.repository.movies.MoviesRepository
import com.octopus.moviesapp.domain.mapper.TrailerMapper
import com.octopus.moviesapp.domain.model.Trailer
import javax.inject.Inject

class FetchMovieTrailerUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val trailerMapper: TrailerMapper
) {
    suspend operator fun invoke(movieId: Int): Trailer {
        return trailerMapper.map(moviesRepository.getMovieTrailerById(movieId))
    }
}