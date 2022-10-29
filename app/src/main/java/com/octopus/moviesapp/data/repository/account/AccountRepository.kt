package com.octopus.moviesapp.data.repository.account

import com.octopus.moviesapp.data.remote.response.LogoutResponse
import com.octopus.moviesapp.data.remote.response.dto.account.AccountDTO
import com.octopus.moviesapp.domain.model.Account
import com.octopus.moviesapp.util.UiState
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    fun getSessionId(): Flow<String?>
    suspend fun login(username: String, password: String): Flow<UiState<Boolean>>
    suspend fun getAccountDetails(sessionId: String): AccountDTO
    suspend fun logout(sessionId: String): LogoutResponse
}

