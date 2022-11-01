package com.octopus.moviesapp.util

import com.octopus.moviesapp.domain.model.Cast
import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.domain.model.MovieDetails
import com.octopus.moviesapp.domain.model.Season
import com.octopus.moviesapp.domain.model.TVShow
import com.octopus.moviesapp.domain.model.TVShowDetails
import com.octopus.moviesapp.domain.model.Trending
import com.octopus.moviesapp.ui.person_details.uistate.PersonDetailsUiState
import com.octopus.moviesapp.ui.person_details.uistate.PersonMovieUiState
import com.octopus.moviesapp.ui.person_details.uistate.PersonTvShowUiState

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
        val tvShows: List<PersonTvShowUiState>
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
