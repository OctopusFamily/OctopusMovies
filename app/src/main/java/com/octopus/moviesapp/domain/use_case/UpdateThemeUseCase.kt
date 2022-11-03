package com.octopus.moviesapp.domain.use_case

import com.octopus.moviesapp.android.local.datastore.DataStorePreferences
import com.octopus.moviesapp.android.local.types.Theme
import com.octopus.moviesapp.util.Constants
import javax.inject.Inject

class UpdateThemeUseCase @Inject constructor(
    private val dataStorePreferences: DataStorePreferences,
) {
    suspend operator fun invoke(newTheme: Theme) {
        dataStorePreferences.writeString(Constants.THEME_KEY, newTheme.name)
    }
}