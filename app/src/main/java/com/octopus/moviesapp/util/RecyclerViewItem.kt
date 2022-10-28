package com.octopus.moviesapp.util

import com.octopus.moviesapp.domain.model.*
import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.domain.model.MovieDetails
import com.octopus.moviesapp.domain.model.TVShow
import com.octopus.moviesapp.ui.tv_show_details.tvShowCastState.TVShowCastUiState
import com.octopus.moviesapp.ui.tv_show_details.tvShowDetailsState.TVShowDetailsUiState
import com.octopus.moviesapp.ui.tv_show_details.tvShowDetailsState.TVShowSeasonUiState

sealed class RecyclerViewItem {
    data class MovieInfoItem(
        val movieDetails: MovieDetails
    ): RecyclerViewItem()

    data class CastItem(
        val castList: List<TVShowCastUiState>
    ): RecyclerViewItem()

    data class TVShowInfoItem(
        val tvShowDetails: TVShowDetailsUiState
    ): RecyclerViewItem()

    data class SeasonItem(
        val seasonsList: List<TVShowSeasonUiState>
    ) : RecyclerViewItem()

    data class ImageSliderItem(
        val title: String,
        val trendingList: List<Trending>
    ): RecyclerViewItem()

    data class PersonInfoDetailsItem(
       val personDetails: PersonDetails
    ) : RecyclerViewItem()
    data class ImageMovieItem(
        val movie: List<Movie>
    ) : RecyclerViewItem()

    data class ImageTvShowItem(
        val tvShows: List<TVShow>
    ) : RecyclerViewItem()
    data class MoviesItem(
        val moviesList: List<Movie>,
    ) : RecyclerViewItem()

    data class TVShowsItem(
        val tvShowsList: List<TVShow>,
    ) : RecyclerViewItem()

    data class TrendingPeopleItem(
        val trendingPeopleList: List<Trending>
    ) : RecyclerViewItem()

}
