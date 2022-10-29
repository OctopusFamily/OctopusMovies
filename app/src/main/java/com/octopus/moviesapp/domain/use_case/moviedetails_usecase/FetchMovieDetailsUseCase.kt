package com.octopus.moviesapp.domain.use_case.moviedetails_usecase

import com.octopus.moviesapp.data.repository.movies.MoviesRepository
import com.octopus.moviesapp.domain.mapper.MovieDetailsMapper
import com.octopus.moviesapp.domain.mapper.MoviesMapper
import com.octopus.moviesapp.domain.model.MovieDetails
import javax.inject.Inject

class FetchMovieDetailsUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val movieDetailsMapper: MovieDetailsMapper,
) {
    suspend operator fun invoke(movieId: Int): MovieDetails {
        return movieDetailsMapper.map(moviesRepository.getMovieDetailsById(movieId))
    }
}