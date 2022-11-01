package com.octopus.moviesapp.domain.use_case.tvshow_details_use_case

import com.octopus.moviesapp.data.repository.tv_shows.TVShowsRepository
import com.octopus.moviesapp.domain.mapper.TVShowsMapper
import com.octopus.moviesapp.domain.mapper.TrailerMapper
import com.octopus.moviesapp.domain.model.Trailer
import javax.inject.Inject

class GetTVShowTrailerUseCase @Inject constructor(
    private val tvShowsRepository: TVShowsRepository,
    private val trailerMapper: TrailerMapper,
) {
    suspend operator fun invoke(tvShowId: Int): Trailer {
        return trailerMapper.map(tvShowsRepository.getTVShowsTrailersById(tvShowId))
    }
}