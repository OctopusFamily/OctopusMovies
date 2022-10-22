package com.octopus.moviesapp.domain.mapper.local

import com.octopus.moviesapp.data.local.database.entity.TrendingEntity
import com.octopus.moviesapp.data.remote.response.dto.TrendingDTO
import com.octopus.moviesapp.domain.mapper.Mapper
import com.octopus.moviesapp.domain.types.MediaType
import com.octopus.moviesapp.util.Constants
import com.octopus.moviesapp.util.buildImageUrl
import javax.inject.Inject

class TrendingEntityMapper @Inject constructor() : Mapper<TrendingDTO, TrendingEntity>() {
    override fun map(input: TrendingDTO): TrendingEntity {
        return TrendingEntity(
            _id = 0,
            trendingId = input.id ?: 0,
            imageUrl = getImageUrlAccordingToMediaName(input),
            mediaType = input.mediaName?.let { MediaType.fromMediaName(it) } ?: MediaType.MOVIE
        )
    }

    private fun getImageUrlAccordingToMediaName(input: TrendingDTO): String {
        return input.mediaName?.let { mediaName ->
            when(MediaType.fromMediaName(mediaName)) {
                MediaType.PERSON -> {
                    buildImageUrl(input.profilePath)
                }
                else -> {
                    buildImageUrl(input.backDropPath)
                }
            }
        } ?: Constants.EMPTY_TEXT
    }
}