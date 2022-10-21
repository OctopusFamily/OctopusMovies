package com.octopus.moviesapp.util

import com.octopus.moviesapp.domain.model.*

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

    data class ImageSliderItem(
        val title: String,
        val trendingList: List<Trending>
    ): RecyclerViewItem()

}
