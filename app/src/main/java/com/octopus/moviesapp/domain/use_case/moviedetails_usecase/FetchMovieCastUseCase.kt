package com.octopus.moviesapp.domain.use_case.moviedetails_usecase

import com.octopus.moviesapp.data.repository.movies.MoviesRepository
import com.octopus.moviesapp.domain.mapper.CastMapper
import com.octopus.moviesapp.domain.mapper.MoviesMapper
import com.octopus.moviesapp.domain.model.Cast
import javax.inject.Inject

class FetchMovieCastUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val castMapper: CastMapper,
) {
    suspend operator fun invoke(tvShowId: Int): List<Cast> {
        return castMapper.map(moviesRepository.getMovieCastById(tvShowId))
    }
}