package com.octopus.moviesapp.domain.use_case

import com.octopus.moviesapp.data.repository.home.HomeRepository
import com.octopus.moviesapp.domain.mapper.TrendingMapper
import com.octopus.moviesapp.domain.model.Trending
import com.octopus.moviesapp.domain.types.MediaType
import javax.inject.Inject

class GetTrendingMediaUseCase @Inject constructor(
    private val homeRepository: HomeRepository,
    private val trendingMapper: TrendingMapper,
) {
    suspend operator fun invoke(mediaType: MediaType): List<Trending> {
        return trendingMapper.mapList(homeRepository.getTrendingMedia(mediaType))
    }
}