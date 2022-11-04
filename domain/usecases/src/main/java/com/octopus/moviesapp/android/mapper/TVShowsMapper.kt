package com.octopus.moviesapp.android.mapper

import com.octopus.moviesapp.android.remote.response.dto.TVShowDTO
import com.octopus.moviesapp.models.model.TVShow
import javax.inject.Inject

class TVShowsMapper @Inject constructor() : Mapper<TVShowDTO, TVShow>() {
    override fun map(input: TVShowDTO): TVShow {
        return TVShow(
            title = input.name ?: "",
            posterImageUrl = buildImageUrl(input.posterImage),
            voteAverage = input.voteAverage ?: 0F,
            id = input.id ?: 0,
            started = convertStringToDate(input.started),
        )
    }
}