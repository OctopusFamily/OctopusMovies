package com.octopus.moviesapp.data.repository

import com.octopus.moviesapp.util.UiState
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    fun getSessionId(): Flow<String?>
    suspend fun login(
        username: String,
        password: String,
    ): Flow<UiState<Boolean>>

}

