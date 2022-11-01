package com.octopus.moviesapp.ui.nested

import com.octopus.moviesapp.R
import com.octopus.moviesapp.domain.model.Movie
import com.octopus.moviesapp.ui.base.BaseAdapter
import com.octopus.moviesapp.ui.person_details.uistate.PersonMovieUiState

class NestedImageMovieAdapter(
    movies:
    List<PersonMovieUiState>,
    listener: NestedImageMovieListener,
) : BaseAdapter<PersonMovieUiState>(movies, listener) {
    override fun layoutId(): Int = R.layout.item_image_movie
    override fun areContentsTheSame(
        oldItem: PersonMovieUiState,
        newItem: PersonMovieUiState
    ): Boolean {
       return oldItem.id == newItem.id
    }

}