package com.octopus.moviesapp.domain.moviedetails_usecase

import com.octopus.moviesapp.data.repository.movies.MoviesRepository
import com.octopus.moviesapp.domain.model.MovieDetails
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository,
) {
    suspend operator fun invoke(movieId: Int): MovieDetails {
        return moviesRepository.getMovieDetailsById(movieId)
    }
}