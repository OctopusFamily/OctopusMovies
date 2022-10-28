package com.octopus.moviesapp.domain.tvshow_details_use_case

import com.octopus.moviesapp.data.repository.tv_shows.TVShowsRepository
import com.octopus.moviesapp.domain.model.TVShowDetails
import com.octopus.moviesapp.domain.model.Trailer
import javax.inject.Inject

class FetchTVShowTrailerUseCase @Inject constructor(
    private val tvShowsRepository: TVShowsRepository,
) {
    suspend operator fun invoke(tvShowId: Int): Trailer {
        return tvShowsRepository.getTVShowsTrailersById(tvShowId)
    }
}