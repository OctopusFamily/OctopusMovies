package com.octopus.moviesapp.android.ui.nested

import com.octopus.moviesapp.android.viewmodels.person_details.uistate.PersonMovieUiState
import com.octopus.moviesapp.android.ui.base.BaseAdapter
import com.octopus.moviesapp.android.viewmodels.nested.NestedImageMovieListener

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