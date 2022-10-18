package com.octopus.moviesapp.domain.mapper

import com.octopus.moviesapp.data.remote.response.dto.TVShowDTO
import com.octopus.moviesapp.domain.model.TVShow
import com.octopus.moviesapp.util.buildImageUrl
import com.octopus.moviesapp.util.convertStringToDate
import javax.inject.Inject

class TVShowMapper @Inject constructor() : Mapper<List<TVShowDTO>, List<TVShow>> {
    override fun map(input: List<TVShowDTO>): List<TVShow> {
        return input.map {
            TVShow(
                title = it.name ?: "",
                posterImageUrl = buildImageUrl(it.posterImage),
                voteAverage = it.voteAverage ?: 0F,
                id = it.id ?: 0,
                started = convertStringToDate(it.started),
            )
        }
    }
}