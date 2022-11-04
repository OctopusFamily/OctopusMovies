package com.octopus.moviesapp.android.usecases.mapper

import com.octopus.moviesapp.android.remote.response.dto.TrendingDTO
import com.octopus.moviesapp.models.model.Trending
import com.octopus.moviesapp.repositories.repository.type.MediaType
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
        } ?: EMPTY_TEXT
    }

    companion object{
        const val EMPTY_TEXT = ""
    }
}