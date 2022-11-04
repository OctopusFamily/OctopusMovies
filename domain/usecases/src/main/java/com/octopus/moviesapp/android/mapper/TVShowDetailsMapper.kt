package com.octopus.moviesapp.android.mapper

import com.octopus.moviesapp.android.remote.response.dto.TVShowDTO
import com.octopus.moviesapp.models.model.TVShowDetails
import com.octopus.moviesapp.repositories.repository.type.GenresType
import javax.inject.Inject

class TVShowDetailsMapper @Inject constructor(
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
            tagline = input.tagline ?:"",
            overview =  input.overview ?: "",
            status = input.status ?: "",
            genres = genresMapper.map(Pair(genresList, GenresType.TV)),
            seasons = seasonsMapper.mapList(seasonsList),
        )
    }
}