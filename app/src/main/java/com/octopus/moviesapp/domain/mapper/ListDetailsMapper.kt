package com.octopus.moviesapp.domain.mapper

import com.octopus.moviesapp.data.remote.response.lists.ListDetailsDto
import com.octopus.moviesapp.domain.model.ListDetails
import com.octopus.moviesapp.util.buildImageUrl
import javax.inject.Inject

class ListDetailsMapper @Inject constructor() : Mapper<List<ListDetailsDto>, List<ListDetails>>() {
    override fun map(input: List<ListDetailsDto>): List<ListDetails> {
        return input.map {
            ListDetails(
                id = it.id ?: 0,
                title = it.title ?: "",
                releaseDate = it.releaseDate ?: "",
                voteAverage = it.voteAverage ?: 0.0,
                posterPath = buildImageUrl(it.posterPath),
            )
        }
    }
}