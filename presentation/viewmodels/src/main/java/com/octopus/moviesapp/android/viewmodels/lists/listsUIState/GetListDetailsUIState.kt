package com.octopus.moviesapp.ui.lists.listsUIState

data class GetListDetailsUIState(
    val listDetails: List<ListDetailsUIState> = emptyList(),
    val isLoading: Boolean = true,
    val isSuccess: Boolean = false,
    val isFailure: Boolean = false,
    val isEmpty: Boolean = false,
    val error: String = ""
)
