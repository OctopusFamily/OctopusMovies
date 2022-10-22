package com.octopus.moviesapp.domain.mapper

import android.content.Context
import com.octopus.moviesapp.R
import com.octopus.moviesapp.data.remote.response.dto.TVShowDTO
import com.octopus.moviesapp.domain.model.TVShowDetails
import com.octopus.moviesapp.domain.types.GenresType
import com.octopus.moviesapp.util.buildImageUrl
import com.octopus.moviesapp.util.convertStringToDate
import com.octopus.moviesapp.util.getTextOrPlaceholder
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class TVShowDetailsMapper @Inject constructor(
    @ApplicationContext private val context: Context,
    private val genresMapper: GenresMapper,
    private val seasonsMapper: SeasonsMapper,
) : Mapper<TVShowDTO, TVShowDetails>() {
    override fun map(input: TVShowDTO): TVShowDetails {
        val genresList = input.genres ?: emptyList()
        val seasonsList = input.seasons ?: emptyList()
        return TVShowDetails(
            id = input.id ?: 0,
            title = input.name ?: "",
            coverImageUrl = buildImageUrl(input.backdropPath),
            posterImageUrl = buildImageUrl(input.posterImage),
            voteCount = input.voteCount ?: 0,
            voteAverage = input.voteAverage ?: 0f,
            episodesNumber =  input.episodesNumber ?: 0,
            seasonsNumber = input.seasonsNumber ?: 0,
            started = convertStringToDate(input.started),
            originalLanguage = input.originalLanguage ?: "",
            tagline = getTextOrPlaceholder(context, input.tagline, R.string.there_is_no_tagline),
            overview = getTextOrPlaceholder(context, input.overview, R.string.there_is_no_overview),
            status = input.status ?: "",
            genres = genresMapper.map(Pair(genresList, GenresType.TV)),
            seasons = seasonsMapper.mapList(seasonsList),
        )
    }
}