package com.octopus.moviesapp.domain.tvshow_details_use_case

import com.octopus.moviesapp.data.repository.tv_shows.TVShowsRepository
import com.octopus.moviesapp.domain.model.TVShowDetails
import com.octopus.moviesapp.ui.tv_show_details.uistate.tvShowDetailsState.TVShowDetailsUiState
import com.octopus.moviesapp.util.buildImageUrl
import javax.inject.Inject

class FetchTVShowDetailsByIdUseCase @Inject constructor(
    private val tvShowsRepository: TVShowsRepository,
) {

   suspend operator fun invoke(tvShowId: Int): TVShowDetails {
    return tvShowsRepository.getTVShowDetailsById(tvShowId)
   }

}