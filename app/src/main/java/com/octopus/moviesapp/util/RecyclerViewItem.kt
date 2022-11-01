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
        val trendingList: List<Trending>
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
        val moviesList: List<Movie>,
    ) : RecyclerViewItem()

    data class TVShowsItem(
        val tvShowsList: List<TVShow>,
    ) : RecyclerViewItem()

    data class TrendingPeopleItem(
        val trendingPeopleList: List<Trending>
    ) : RecyclerViewItem()

}
