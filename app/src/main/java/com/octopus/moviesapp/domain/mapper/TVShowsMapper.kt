package com.octopus.moviesapp.domain.mapper

import com.octopus.moviesapp.data.remote.response.dto.TVShowDTO
import com.octopus.moviesapp.domain.model.TVShow
import com.octopus.moviesapp.util.buildImageUrl
import com.octopus.moviesapp.util.convertStringToDate
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