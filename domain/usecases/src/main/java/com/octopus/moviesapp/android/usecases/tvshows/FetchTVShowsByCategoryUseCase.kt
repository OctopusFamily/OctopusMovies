package com.octopus.moviesapp.android.usecases


import com.octopus.moviesapp.android.usecases.mapper.TVShowsMapper
import com.octopus.moviesapp.models.model.TVShow
import com.octopus.moviesapp.repositories.repository.tv_shows.TVShowsRepository
import com.octopus.moviesapp.repositories.repository.type.TVShowsCategory
import javax.inject.Inject

class FetchTVShowsByCategoryUseCase @Inject constructor(
    private val tvShowsRepository: TVShowsRepository,
    private val tvShowsMapper: TVShowsMapper,
) {
    suspend operator fun invoke(tvShowsCategory: TVShowsCategory, page: Int): List<TVShow> {
        return tvShowsMapper.mapList(tvShowsRepository.getTVShowsByCategory(tvShowsCategory, page))
    }
}