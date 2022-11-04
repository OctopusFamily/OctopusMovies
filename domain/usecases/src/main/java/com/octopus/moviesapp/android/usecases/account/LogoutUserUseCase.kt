package com.octopus.moviesapp.android.usecases.account

import com.octopus.moviesapp.android.mapper.LogoutMapper
import com.octopus.moviesapp.models.model.Logout
import com.octopus.moviesapp.repositories.repository.account.AccountRepository
import javax.inject.Inject

class LogoutUserUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    private val logoutMapper: LogoutMapper,
//    private val dataStorePreferences: DataStorePreferences,
) {
    suspend operator fun invoke(sessionId: String): Logout {
        return logoutMapper.map(accountRepository.logout(sessionId))
    }

//    suspend fun removeUserFromDataStore() {
//        dataStorePreferences.writeString(Constants.SESSION_ID_KEY, "")
//    }
}