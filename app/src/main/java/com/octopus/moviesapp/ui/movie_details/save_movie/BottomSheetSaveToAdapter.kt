package com.octopus.moviesapp.ui.movie_details.save_movie

import com.octopus.moviesapp.R
import com.octopus.moviesapp.ui.base.BaseAdapter
import com.octopus.moviesapp.ui.lists.MyListsClicksListener
import com.octopus.moviesapp.ui.lists.listsUIState.CreatedListsUIState

class BottomSheetSaveToAdapter(
    items: List<CreatedListsUIState>,
    listener: MyListsClicksListener,
) : BaseAdapter<CreatedListsUIState>(items, listener) {
    override fun layoutId(): Int = R.layout.item_save_to_list
    override fun areContentsTheSame(oldItem: CreatedListsUIState, newItem: CreatedListsUIState): Boolean {
        return oldItem.listID == newItem.listID
    }
}