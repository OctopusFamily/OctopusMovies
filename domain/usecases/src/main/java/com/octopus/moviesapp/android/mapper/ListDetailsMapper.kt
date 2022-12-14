package com.octopus.moviesapp.android.mapper

import com.octopus.moviesapp.android.remote.response.lists.ListDetailsDto
import com.octopus.moviesapp.models.model.ListDetails
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