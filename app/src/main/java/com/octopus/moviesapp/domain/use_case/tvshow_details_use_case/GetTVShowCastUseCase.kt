package com.octopus.moviesapp.domain.use_case.tvshow_details_use_case

import com.octopus.moviesapp.data.repository.tv_shows.TVShowsRepository
import com.octopus.moviesapp.domain.mapper.CastMapper
import com.octopus.moviesapp.domain.model.Cast
import javax.inject.Inject

class GetTVShowCastUseCase @Inject constructor(
    private val tvShowsRepository: TVShowsRepository,
    private val castMapper: CastMapper,
) {
    suspend operator fun invoke(tvShowId: Int): List<Cast> {
        return castMapper.map(tvShowsRepository.getTVShowCastById(tvShowId))
    }
}