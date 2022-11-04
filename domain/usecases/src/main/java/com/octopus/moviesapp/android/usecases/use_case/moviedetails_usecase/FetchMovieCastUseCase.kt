package com.octopus.moviesapp.android.usecases.use_case.moviedetails_usecase


import com.octopus.moviesapp.android.usecases.mapper.CastMapper
import com.octopus.moviesapp.models.model.Cast
import com.octopus.moviesapp.repositories.repository.movies.MoviesRepository
import javax.inject.Inject

class FetchMovieCastUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val castMapper: CastMapper,
) {
    suspend operator fun invoke(tvShowId: Int): List<Cast> {
        return castMapper.map(moviesRepository.getMovieCastById(tvShowId))
    }
}