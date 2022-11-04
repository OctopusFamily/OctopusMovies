package com.octopus.moviesapp.android.usecases.movies

import com.octopus.moviesapp.android.usecases.mapper.MovieDetailsMapper
import com.octopus.moviesapp.models.model.MovieDetails
import com.octopus.moviesapp.repositories.repository.movies.MoviesRepository
import javax.inject.Inject

class FetchMovieDetailsUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val movieDetailsMapper: MovieDetailsMapper,
) {
    suspend operator fun invoke(movieId: Int): MovieDetails {
        return movieDetailsMapper.map(moviesRepository.getMovieDetailsById(movieId))
    }
}