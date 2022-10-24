package com.octopus.moviesapp.ui.lists

import com.octopus.moviesapp.R
import com.octopus.moviesapp.domain.model.ListDetails
import com.octopus.moviesapp.ui.base.BaseAdapter

class ListDetailsAdapter(
    lists: List<ListDetails>,
    listener: ListDetailsInteractionListener
) : BaseAdapter<ListDetails>(lists,listener) {
    override fun layoutId(): Int = R.layout.item_list_details
    override fun areContentsTheSame(oldItem: ListDetails, newItem: ListDetails): Boolean {
        return oldItem.id == newItem.id
    }

}