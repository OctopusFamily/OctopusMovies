package com.octopus.moviesapp.domain.use_case.moviedetails_usecase

import com.octopus.moviesapp.data.repository.movies.MoviesRepository
import com.octopus.moviesapp.domain.model.MovieDetails
import com.octopus.moviesapp.domain.model.Trailer
import javax.inject.Inject

class GetMovieTrailerUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository,
) {
    suspend operator fun invoke(movieId: Int): Trailer {
        return moviesRepository.getMovieTrailerById(movieId)
    }
}