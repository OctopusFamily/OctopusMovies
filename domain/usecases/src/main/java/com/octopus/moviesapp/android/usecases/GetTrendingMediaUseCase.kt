package com.octopus.moviesapp.android.usecases.usecase

import com.octopus.moviesapp.android.usecases.mapper.TrendingMapper
import com.octopus.moviesapp.models.model.Trending
import com.octopus.moviesapp.models.type.MediaType
import com.octopus.moviesapp.repositories.repository.home.HomeRepository
import javax.inject.Inject

class GetTrendingMediaUseCase @Inject constructor(
    private val homeRepository: HomeRepository,
    private val trendingMapper: TrendingMapper,
) {
    suspend operator fun invoke(mediaType: MediaType): List<Trending> {
        return trendingMapper.mapList(homeRepository.getTrendingMedia(mediaType))
    }
}