package com.octopus.moviesapp.android.ui.lists

import com.octopus.moviesapp.android.viewmodels.lists.ListDetailsInteractionListener
import com.octopus.moviesapp.ui.lists.listsUIState.ListDetailsUIState

class ListDetailsAdapter(
    lists: List<ListDetailsUIState>,
    listener: ListDetailsInteractionListener
) : BaseAdapter<ListDetailsUIState>(lists,listener) {
    override fun layoutId(): Int = R.layout.item_list_details
    override fun areContentsTheSame(oldItem: ListDetailsUIState, newItem: ListDetailsUIState): Boolean {
        return oldItem.id == newItem.id
    }

}