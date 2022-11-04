package com.octopus.moviesapp.android.viewmodels.lists

import com.octopus.moviesapp.models.model.CreatedList
import com.octopus.moviesapp.ui.lists.listsUIState.CreatedListsUIState

fun CreatedList.toCreatedListUIState(): CreatedListsUIState {
    return CreatedListsUIState(
        listName = name,
        itemCounts = itemCount,
        listID = id
    )
}

