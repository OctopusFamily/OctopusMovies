package com.octopus.moviesapp.android.usecases


import com.octopus.moviesapp.repositories.repository.Constants
import javax.inject.Inject

class UpdateThemeUseCase @Inject constructor(
    private val dataStorePreferences: DataStorePreferences,
) {
    suspend operator fun invoke(newTheme: Theme) {
        dataStorePreferences.writeString(Constants.THEME_KEY, newTheme.name)
    }
}