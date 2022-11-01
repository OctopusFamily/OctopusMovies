package com.octopus.moviesapp.ui.lists.listsUIState

data class ListsUIState(
    val createdLists: List<CreatedListsUIState> = emptyList(),
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isFailure: Boolean = false,
    val isEmpty: Boolean = false,
    val error: String = ""
)