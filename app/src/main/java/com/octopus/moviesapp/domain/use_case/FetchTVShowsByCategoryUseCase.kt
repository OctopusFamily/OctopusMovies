package com.octopus.moviesapp.domain.use_case

import com.octopus.moviesapp.data.repository.tv_shows.TVShowsRepository
import com.octopus.moviesapp.domain.mapper.TVShowsMapper
import com.octopus.moviesapp.domain.model.TVShow
import com.octopus.moviesapp.domain.types.TVShowsCategory
import javax.inject.Inject

class FetchTVShowsByCategoryUseCase @Inject constructor(
    private val tvShowsRepository: TVShowsRepository,
    private val tvShowsMapper: TVShowsMapper,
) {
    suspend operator fun invoke(tvShowsCategory: TVShowsCategory, page: Int): List<TVShow> {
        return tvShowsMapper.mapList(tvShowsRepository.getTVShowsByCategory(tvShowsCategory, page))
    }
}