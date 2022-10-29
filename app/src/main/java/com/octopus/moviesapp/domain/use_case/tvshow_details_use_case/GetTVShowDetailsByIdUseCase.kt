package com.octopus.moviesapp.domain.use_case.tvshow_details_use_case

import com.octopus.moviesapp.data.repository.tv_shows.TVShowsRepository
import com.octopus.moviesapp.domain.model.TVShowDetails
import javax.inject.Inject

class GetTVShowDetailsByIdUseCase @Inject constructor(
    private val tvShowsRepository: TVShowsRepository,
) {

   suspend operator fun invoke(tvShowId: Int): TVShowDetails {
    return tvShowsRepository.getTVShowDetailsById(tvShowId)
   }

}