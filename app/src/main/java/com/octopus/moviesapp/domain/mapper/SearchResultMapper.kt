package com.octopus.moviesapp.domain.mapper

import com.octopus.moviesapp.data.remote.response.dto.SearchDTO
import com.octopus.moviesapp.domain.model.SearchResult
import com.octopus.moviesapp.util.buildImageUrl
import com.octopus.moviesapp.util.convertStringToDate
import com.octopus.moviesapp.util.getTextOrPlaceholder
import dagger.hilt.android.qualifiers.ApplicationContext
import android.content.Context
import com.octopus.moviesapp.R
import com.octopus.moviesapp.domain.types.MediaType
import javax.inject.Inject

class SearchResultMapper @Inject constructor(
    @ApplicationContext private val context: Context,
) : Mapper<SearchDTO, SearchResult>() {
    override fun map(input: SearchDTO): SearchResult {
        return SearchResult(
            id = input.id ?: 0,
            title = input.title ?: input.originalTitle?: input.name ?: "",
            posterImageUrl = buildImageUrl(input.posterPath?: input.backdropPath ?: input.profile_path),
            voteAverage = input.voteAverage ?: 0f,
            releaseDate = convertStringToDate(input.releaseDate),
            mediaType = input.mediaType?.let { mediaType -> MediaType.fromMediaName(mediaType) } ?: MediaType.MOVIE,
            originalLanguage = input.originalLanguage?: "",
            overview = getTextOrPlaceholder(context, input.overview, R.string.there_is_no_overview)
        )
    }
}