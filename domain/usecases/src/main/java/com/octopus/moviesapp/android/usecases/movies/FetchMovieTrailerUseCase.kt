package com.octopus.moviesapp.android.usecases.movies

import com.octopus.moviesapp.android.mapper.TrailerMapper
import com.octopus.moviesapp.models.model.Trailer
import com.octopus.moviesapp.repositories.repository.movies.MoviesRepository
import javax.inject.Inject

class FetchMovieTrailerUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val trailerMapper: TrailerMapper,
) {
    suspend operator fun invoke(movieId: Int): Trailer {
        return trailerMapper.map(moviesRepository.getMovieTrailerById(movieId))
    }
}