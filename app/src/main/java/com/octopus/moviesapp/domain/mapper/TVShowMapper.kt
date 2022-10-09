package com.octopus.moviesapp.domain.mapper

import com.octopus.moviesapp.data.remote.response.dto.TVShowDTO
import com.octopus.moviesapp.domain.model.TVShow
import com.octopus.moviesapp.util.buildUrl
import com.octopus.moviesapp.util.convertToDate
import java.util.*

class TVShowMapper : Mapper<List<TVShowDTO>, List<TVShow>> {
    override fun map(input: List<TVShowDTO>): List<TVShow> {
        return input.map {
            TVShow(
                title = it.name ?: "",
                posterImageUrl = it.posterImage?.buildUrl() ?: "",
                voteAverage = it.voteAverage ?: 0F,
                genres = it.genres ?: emptyList(),
                id = it.id ?: 0,
                started = it.started?.convertToDate() ?: Date()
            )
        }
    }
}