package com.octopus.moviesapp.util

import com.octopus.moviesapp.domain.model.*
import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.domain.model.MovieDetails
import com.octopus.moviesapp.domain.model.TVShow
import com.octopus.moviesapp.ui.movie_details.uistate.MovieDetailsUiState
import com.octopus.moviesapp.ui.tv_show_details.uistate.CastUiState
import com.octopus.moviesapp.ui.tv_show_details.uistate.TVShowDetailsUiState
import com.octopus.moviesapp.ui.tv_show_details.uistate.SeasonUiState
import com.octopus.moviesapp.domain.model.TVShowDetails
import com.octopus.moviesapp.ui.home.uistate.TrendingUiState
import com.octopus.moviesapp.ui.movies.uistate.MovieUiState
import com.octopus.moviesapp.ui.tv_shows.uistate.TVShowUiState

sealed class RecyclerViewItem {
    data class MovieInfoItem(
        val movieDetails: MovieDetailsUiState
    ): RecyclerViewItem()

    data class CastItem(
        val castList: List<CastUiState>
    ): RecyclerViewItem()

    data class TVShowInfoItem(
        val tvShowDetails: TVShowDetailsUiState
    ): RecyclerViewItem()

    data class SeasonItem(
        val seasonsList: List<SeasonUiState>
    ) : RecyclerViewItem()

    data class ImageSliderItem(
        val title: String,
        val trendingList: List<TrendingUiState>
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
        val moviesList: List<MovieUiState>,
    ) : RecyclerViewItem()

    data class TVShowsItem(
        val tvShowsList: List<TVShowUiState>,
    ) : RecyclerViewItem()

    data class TrendingPeopleItem(
        val trendingPeopleList: List<TrendingUiState>
    ) : RecyclerViewItem()

}
