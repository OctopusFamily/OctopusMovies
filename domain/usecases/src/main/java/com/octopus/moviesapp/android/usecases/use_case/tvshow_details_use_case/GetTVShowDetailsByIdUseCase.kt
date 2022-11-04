package com.octopus.moviesapp.android.usecases.use_case.tvshow_details_use_case

import com.octopus.moviesapp.android.usecases.mapper.TVShowDetailsMapper
import com.octopus.moviesapp.models.model.TVShowDetails
import com.octopus.moviesapp.repositories.repository.tv_shows.TVShowsRepository
import javax.inject.Inject

class GetTVShowDetailsByIdUseCase @Inject constructor(
    private val tvShowsRepository: TVShowsRepository,
    private val tvShowDetailsMapper: TVShowDetailsMapper,
) {

   suspend operator fun invoke(tvShowId: Int): TVShowDetails {
    return tvShowDetailsMapper.map(tvShowsRepository.getTVShowDetailsById(tvShowId))
   }

}