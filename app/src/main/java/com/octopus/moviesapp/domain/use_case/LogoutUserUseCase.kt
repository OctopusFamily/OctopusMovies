package com.octopus.moviesapp.domain.use_case

import com.octopus.moviesapp.data.local.datastore.DataStorePreferences
import com.octopus.moviesapp.data.repository.account.AccountRepository
import com.octopus.moviesapp.domain.mapper.LogoutMapper
import com.octopus.moviesapp.domain.model.Logout
import com.octopus.moviesapp.util.Constants
import javax.inject.Inject

class LogoutUserUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    private val logoutMapper: LogoutMapper,
    private val dataStorePreferences: DataStorePreferences,
) {
    suspend operator fun invoke(sessionId: String): Logout {
        return logoutMapper.map(accountRepository.logout(sessionId))
    }

    suspend fun removeUserFromDataStore() {
        dataStorePreferences.writeString(Constants.SESSION_ID_KEY, "")
    }
}