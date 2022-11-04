package com.octopus.moviesapp.android.usecases

import com.octopus.moviesapp.repositories.repository.tv_shows.TVShowsRepository
import com.octopus.moviesapp.repositories.repository.type.TVShowsCategory
import javax.inject.Inject

class GetTVShowsPagingSourceUseCase @Inject constructor(
    private val tvShowsRepository: TVShowsRepository
) {
    operator fun invoke(tvShowsCategory: TVShowsCategory): TVShowsPagingSource {
        return tvShowsRepository.getTVShowPagingSource(tvShowsCategory)
    }
}
