package com.octopus.moviesapp.android.usecases.use_case.tvshow_details_use_case

import com.octopus.moviesapp.android.usecases.mapper.CastMapper
import com.octopus.moviesapp.models.model.Cast
import com.octopus.moviesapp.repositories.repository.tv_shows.TVShowsRepository
import javax.inject.Inject

class GetTVShowCastUseCase @Inject constructor(
    private val tvShowsRepository: TVShowsRepository,
    private val castMapper: CastMapper,
) {
    suspend operator fun invoke(tvShowId: Int): List<Cast> {
        return castMapper.map(tvShowsRepository.getTVShowCastById(tvShowId))
    }
}