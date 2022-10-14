package com.octopus.moviesapp.domain.mapper

import com.octopus.moviesapp.data.remote.response.dto.TVShowDTO
import com.octopus.moviesapp.domain.enums.GenresType
import com.octopus.moviesapp.domain.model.TVShowDetails
import com.octopus.moviesapp.util.buildImageUrl
import com.octopus.moviesapp.util.convertToDate
import java.util.*
import javax.inject.Inject

class TVShowDetailsMapper @Inject constructor(
    private val genresMapper: GenresMapper,
    private val seasonsMapper: SeasonsMapper,
) : Mapper<TVShowDTO, TVShowDetails> {
    override fun map(input: TVShowDTO): TVShowDetails {
        val genresList = input.genres ?: emptyList()
        val seasonsList = input.seasons ?: emptyList()
        return TVShowDetails(
            id = input.id ?: 0,
            title = input.name ?: "",
            coverImageUrl = input.backdropPath?.buildImageUrl() ?: "",
            posterImageUrl = input.posterImage?.buildImageUrl() ?: "",
            voteCount = input.voteCount ?: 0,
            voteAverage = input.voteAverage ?: 0f,
            episodesNumber =  input.episodesNumber ?: 0,
            seasonsNumber = input.seasonsNumber ?: 0,
            started = input.started?.convertToDate() ?: Date(),
            originalLanguage = input.originalLanguage ?: "",
            tagline = input.tagline ?: "",
            overview = input.overview ?: "",
            status = input.status ?: "",
            genres = genresMapper.map(Pair(genresList, GenresType.TV)),
            seasons = seasonsMapper.map(seasonsList),
        )
    }
}