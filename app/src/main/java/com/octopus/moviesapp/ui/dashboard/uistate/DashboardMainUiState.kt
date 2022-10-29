package com.octopus.moviesapp.ui.dashboard.uistate

data class DashboardMainUiState(
    val isLoggedIn: Boolean = false,
    val isProfileLoading: Boolean = false,
    val isProfileError: Boolean = false,
    val isProfileSuccess: Boolean = false,
    val profileUiState: ProfileUiState = ProfileUiState("", "", ""),
)