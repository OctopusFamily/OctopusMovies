package com.octopus.moviesapp.domain.mapper

import com.octopus.moviesapp.data.remote.response.dto.TrendingDTO
import com.octopus.moviesapp.domain.model.Trending
import com.octopus.moviesapp.domain.types.MediaType
import com.octopus.moviesapp.util.Constants
import com.octopus.moviesapp.util.buildImageUrl
import javax.inject.Inject

class TrendingMapper @Inject constructor() : Mapper<TrendingDTO, Trending>() {
    override fun map(input: TrendingDTO): Trending {
        return Trending(
            id = input.id ?: 0,
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