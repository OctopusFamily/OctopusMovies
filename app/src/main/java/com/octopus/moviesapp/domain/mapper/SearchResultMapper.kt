package com.octopus.moviesapp.domain.mapper

import com.octopus.moviesapp.data.remote.response.dto.SearchDTO
import com.octopus.moviesapp.domain.model.SearchResult
import com.octopus.moviesapp.util.buildImageUrl
import com.octopus.moviesapp.util.convertStringToDate
import com.octopus.moviesapp.util.getTextOrPlaceholder
import dagger.hilt.android.qualifiers.ApplicationContext
import android.content.Context
import com.octopus.moviesapp.R
import javax.inject.Inject

class SearchResultMapper @Inject constructor(
    @ApplicationContext private val context: Context,
) : Mapper<List<SearchDTO>, List<SearchResult>> {
    override fun map(input: List<SearchDTO>): List<SearchResult> {
        return input.map {
            SearchResult(
                id = it.id ?: 0,
                title = it.title ?:it.originalTitle?: it.name?: "",
                posterImageUrl = buildImageUrl(it.posterPath?:it.backdropPath ?:it.profile_path ),
                voteAverage = it.voteAverage ?: 0f,
                releaseDate = convertStringToDate(it.releaseDate) ,
                mediaType = it.mediaType ?: "",
                originalLanguage = it.originalLanguage?: "",
                overview = getTextOrPlaceholder(context,it.overview, R.string.there_is_no_overview)
            )
        }
    }
}