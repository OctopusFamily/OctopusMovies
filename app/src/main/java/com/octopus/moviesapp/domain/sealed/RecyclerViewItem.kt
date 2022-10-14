package com.octopus.moviesapp.domain.sealed

import com.octopus.moviesapp.domain.model.Cast
import com.octopus.moviesapp.domain.model.MovieDetails
import com.octopus.moviesapp.domain.model.Season
import com.octopus.moviesapp.domain.model.TVShowDetails

sealed class RecyclerViewItem {
    data class MovieInfoItem(
        val movieDetails: MovieDetails
    ): RecyclerViewItem()

    data class CastItem(
        val castList: List<Cast>
    ): RecyclerViewItem()

    data class TVShowInfoItem(
        val tvShowDetails: TVShowDetails
    ): RecyclerViewItem()

    data class SeasonItem(
        val seasonsList: List<Season>
    ): RecyclerViewItem()

}
