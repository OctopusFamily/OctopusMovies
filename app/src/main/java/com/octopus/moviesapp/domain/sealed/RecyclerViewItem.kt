package com.octopus.moviesapp.domain.sealed

import com.octopus.moviesapp.domain.model.Cast
import com.octopus.moviesapp.domain.model.MovieDetails

sealed class RecyclerViewItem {
    data class MovieDetailsInfoItem(
        val movieDetails: MovieDetails
    ): RecyclerViewItem()

    data class MovieDetailsCastItem(
        val castList: List<Cast>
    ): RecyclerViewItem()
}
