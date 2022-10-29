package com.octopus.moviesapp.domain.use_case.moviedetails_usecase

import com.octopus.moviesapp.data.repository.movies.MoviesRepository
import com.octopus.moviesapp.data.repository.tv_shows.TVShowsRepository
import com.octopus.moviesapp.domain.model.Cast
import javax.inject.Inject

class GetMovieCastUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {
    suspend operator fun invoke(tvShowId: Int): List<Cast> {
        return moviesRepository.getMovieCastById(tvShowId)
    }
}