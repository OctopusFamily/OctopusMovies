package com.octopus.moviesapp.ui.lists

import com.octopus.moviesapp.domain.mapper.Mapper
import com.octopus.moviesapp.domain.model.CreatedList
import com.octopus.moviesapp.ui.lists.listsUIState.CreatedListsUIState
import javax.inject.Inject

class CreatedListsUIMapper @Inject constructor() : Mapper<CreatedList, CreatedListsUIState>() {

    override fun map(input: CreatedList): CreatedListsUIState {
        return CreatedListsUIState(
            listName = input.name,
            itemCounts = input.itemCount,
            listID = input.id
        )
    }
}


