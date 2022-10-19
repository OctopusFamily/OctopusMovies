package com.octopus.moviesapp.domain.mapper

import com.octopus.moviesapp.data.remote.response.dto.SearchDTO
import com.octopus.moviesapp.domain.model.SearchResult
import com.octopus.moviesapp.util.buildImageUrl
import com.octopus.moviesapp.util.convertToDate
import java.util.*
import javax.inject.Inject

class SearchResultMapper @Inject constructor() : Mapper<List<SearchDTO>, List<SearchResult>> {
    override fun map(input: List<SearchDTO>): List<SearchResult> {
        return input.map {
            SearchResult(
                id = it.id ?: 0,
                title = it.title ?:it.originalTitle?: it.name?: "",
                posterImageUrl = it.posterPath?.buildImageUrl() ?: it.backdropPath?.buildImageUrl() ?: it.profile_path?.buildImageUrl() ?: "",
                voteAverage = it.voteAverage ?: 0f,
                releaseDate = it.releaseDate?.convertToDate() ?: it.firstAirDate?.convertToDate()?: Date(),
                mediaType = it.mediaType ?: "",
                originalLanguage = it.originalLanguage?: "",
                overview = it.overview?: "Does no have info"
            )
        }
    }
}