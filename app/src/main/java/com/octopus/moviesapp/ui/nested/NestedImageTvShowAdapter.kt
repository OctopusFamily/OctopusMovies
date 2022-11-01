package com.octopus.moviesapp.ui.nested

import com.octopus.moviesapp.R
import com.octopus.moviesapp.ui.base.BaseAdapter
import com.octopus.moviesapp.ui.person_details.uistate.PersonTVShowUiState

class NestedImageTvShowAdapter(
    tvShows: List<PersonTVShowUiState>,
    listener: NestedImageTvShowListener,
) : BaseAdapter<PersonTVShowUiState>(tvShows, listener) {
    override fun layoutId(): Int = R.layout.item_image_tv_show
    override fun areContentsTheSame(
        oldItem: PersonTVShowUiState,
        newItem: PersonTVShowUiState
    ): Boolean {
        TODO("Not yet implemented")
    }

}