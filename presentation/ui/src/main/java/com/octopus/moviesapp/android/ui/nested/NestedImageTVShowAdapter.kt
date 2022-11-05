package com.octopus.moviesapp.android.ui.nested

import com.octopus.moviesapp.android.viewmodels.person_details.uistate.PersonTVShowUiState
import com.octopus.moviesapp.android.ui.base.BaseAdapter
import com.octopus.moviesapp.android.viewmodels.nested.NestedImageTVShowListener

class NestedImageTVShowAdapter(
    tvShows: List<PersonTVShowUiState>,
    listener: NestedImageTVShowListener,
) : BaseAdapter<PersonTVShowUiState>(tvShows, listener) {
    override fun layoutId(): Int = R.layout.item_image_tv_show
    override fun areContentsTheSame(
        oldItem: PersonTVShowUiState,
        newItem: PersonTVShowUiState
    ): Boolean {
        return oldItem.id == newItem.id
    }

}