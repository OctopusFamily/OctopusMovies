package com.octopus.moviesapp.android.viewmodels.tvshow_details.mapper

import com.octopus.moviesapp.android.mapper.buildImageUrl
import com.octopus.moviesapp.android.viewmodels.tvshow_details.uistate.*
import com.octopus.moviesapp.models.model.*
import com.octopus.moviesapp.models.type.GenresType


fun Trailer.asTrailerUiState(): TrailerUiState {
    return TrailerUiState(
        url = url
    )
}

fun TVShowDetails.asDetailsUiState(): DetailsUiState {
    return DetailsUiState(
        id = id,
        title = title,
        coverImageUrl = buildImageUrl(coverImageUrl),
        posterImageUrl = buildImageUrl(posterImageUrl),
        voteCount = voteCount,
        voteAverage = voteAverage,
        episodesNumber = episodesNumber,
        seasonsNumber = seasonsNumber,
        started = started,
        originalLanguage = originalLanguage,
        tagline = tagline,
        overview = overview,
        status = status,
        genres = genres.map { genre -> genre.asGenresUiState() },
        seasons = seasons.map { season -> season.asSeasonUiState() }
    )
}

fun Genre.asGenresUiState(): GenresUiState {
    return GenresUiState(
        id = id,
        name = name,
        type = GenresType.TV.pathName,
    )
}

fun Season.asSeasonUiState(): SeasonUiState {
    return SeasonUiState(
        id = id,
        seasonNumber = seasonNumber,
        imageUrl = buildImageUrl(imageUrl),
    )
}

fun Cast.asCastUiState(): CastUiState {
    return CastUiState(
        id = id,
        name = name,
        profileImageUrl = buildImageUrl(profileImageUrl),
    )
}