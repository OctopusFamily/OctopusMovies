package com.octopus.moviesapp.android.usecases.tvshows

import com.octopus.moviesapp.models.model.Cast
import com.octopus.moviesapp.repositories.repository.tv_shows.TVShowsRepository
import javax.inject.Inject

class GetTVShowCastUseCase @Inject constructor(
    private val tvShowsRepository: TVShowsRepository,
    private val castMapper: com.octopus.moviesapp.android.mapper.CastMapper,
) {
    suspend operator fun invoke(tvShowId: Int): List<Cast> {
        return castMapper.map(tvShowsRepository.getTVShowCastById(tvShowId))
    }
}