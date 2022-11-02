package com.octopus.moviesapp.util

import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.domain.model.TVShow
import com.octopus.moviesapp.domain.model.Trending
import com.octopus.moviesapp.ui.movie_details.uistate.MovieDetailsUiState
import com.octopus.moviesapp.ui.person_details.uistate.PersonDetailsUiState
import com.octopus.moviesapp.ui.person_details.uistate.PersonMovieUiState
import com.octopus.moviesapp.ui.person_details.uistate.PersonTVShowUiState
import com.octopus.moviesapp.ui.tv_show_details.uistate.CastUiState
import com.octopus.moviesapp.ui.tv_show_details.uistate.DetailsUiState
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
        val tvShowDetails: DetailsUiState
    ): RecyclerViewItem()

    data class SeasonItem(
        val seasonsList: List<SeasonUiState>
    ) : RecyclerViewItem()

    data class ImageSliderItem(
        val title: String,
        val trendingList: List<TrendingUiState>
    ): RecyclerViewItem()

    data class PersonInfoDetailsItem(
        val personDetails: PersonDetailsUiState
    ) : RecyclerViewItem()
    data class ImageMovieItem(
        val movie: List<PersonMovieUiState>
    ) : RecyclerViewItem()

    data class ImageTvShowItem(
        val tvShows: List<PersonTVShowUiState>
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
