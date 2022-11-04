package com.octopus.moviesapp.android.mapper

import com.octopus.moviesapp.android.remote.response.dto.SearchDTO
import com.octopus.moviesapp.models.model.SearchResult
import com.octopus.moviesapp.repositories.repository.type.MediaType
import javax.inject.Inject

class SearchResultMapper @Inject constructor() : Mapper<SearchDTO, SearchResult>() {
    override fun map(input: SearchDTO): SearchResult {
        return SearchResult(
            id = input.id ?: 0,
            title = input.title ?: input.originalTitle?: input.name ?: "",
            posterImageUrl = buildImageUrl(input.posterPath?: input.backdropPath ?: input.profile_path),
            voteAverage = input.voteAverage ?: 0f,
            releaseDate = convertStringToDate(input.releaseDate),
            mediaType = input.mediaType?.let { mediaType -> MediaType.fromMediaName(mediaType) } ?: MediaType.MOVIE,
            originalLanguage = input.originalLanguage?: "",
            overview = input.overview ?: ""
        )
    }
}