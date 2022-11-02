package com.octopus.moviesapp.domain.use_case

import com.octopus.moviesapp.data.remote.pagingsource.TVShowsPagingSource
import com.octopus.moviesapp.data.repository.tv_shows.TVShowsRepository
import com.octopus.moviesapp.domain.types.TVShowsCategory
import javax.inject.Inject

class GetTVShowsPagingSourceUseCase @Inject constructor(
    private val tvShowsRepository: TVShowsRepository
) {
    operator fun invoke(tvShowsCategory: TVShowsCategory): TVShowsPagingSource {
        return tvShowsRepository.getTVShowPagingSource(tvShowsCategory)
    }
}
