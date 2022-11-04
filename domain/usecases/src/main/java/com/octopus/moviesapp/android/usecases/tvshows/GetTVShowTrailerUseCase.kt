package com.octopus.moviesapp.android.usecases.tvshows

import com.octopus.moviesapp.android.usecases.mapper.TrailerMapper
import com.octopus.moviesapp.models.model.Trailer
import com.octopus.moviesapp.repositories.repository.tv_shows.TVShowsRepository
import javax.inject.Inject

class GetTVShowTrailerUseCase @Inject constructor(
    private val tvShowsRepository: TVShowsRepository,
    private val trailerMapper: TrailerMapper,
) {
    suspend operator fun invoke(tvShowId: Int): Trailer {
        return trailerMapper.map(tvShowsRepository.getTVShowsTrailersById(tvShowId))
    }
}