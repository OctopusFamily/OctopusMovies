package com.octopus.moviesapp.ui.nested

import com.octopus.moviesapp.R
import com.octopus.moviesapp.ui.base.BaseAdapter
import com.octopus.moviesapp.ui.person_details.uistate.PersonTvShowUiState

class NestedImageTvShowAdapter(
    tvShows: List<PersonTvShowUiState>,
    listener: NestedImageTvShowListener,
) : BaseAdapter<PersonTvShowUiState>(tvShows, listener) {
    override fun layoutId(): Int = R.layout.item_image_tv_show
    override fun areContentsTheSame(
        oldItem: PersonTvShowUiState,
        newItem: PersonTvShowUiState
    ): Boolean {
        TODO("Not yet implemented")
    }

}