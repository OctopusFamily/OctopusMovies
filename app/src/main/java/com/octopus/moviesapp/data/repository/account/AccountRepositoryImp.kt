package com.octopus.moviesapp.data.repository.account

import com.octopus.moviesapp.data.JsonParser
import com.octopus.moviesapp.data.local.datastore.DataStorePreferences
import com.octopus.moviesapp.data.remote.response.LogoutResponse
import com.octopus.moviesapp.data.remote.response.dto.account.AccountDTO
import com.octopus.moviesapp.data.remote.response.login.ErrorResponse
import com.octopus.moviesapp.data.remote.service.TMDBApiService
import com.octopus.moviesapp.util.Constants
import com.octopus.moviesapp.util.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class AccountRepositoryImp @Inject constructor(
    private val service: TMDBApiService,
    private val dataStorePreferences: DataStorePreferences,
    private val jsonParser: JsonParser,
) : AccountRepository {
    override fun getSessionId(): Flow<String?> {
        return dataStorePreferences.readString(Constants.SESSION_ID_KEY)
    }

    override suspend fun login(
        username: String,
        password: String,
    ): Flow<UiState<Boolean>> {
        return flow {
            emit(UiState.Loading)
            try {
                val token = getRequestToken().toString()
                val body = mapOf(
                    "username" to username,
                    "password" to password,
                    "request_token" to token,
                ).toMap()
                val validateRequestTokenWithLogin = service.validateRequestTokenWithLogin(body)
                if (validateRequestTokenWithLogin.isSuccessful) {
                    validateRequestTokenWithLogin.body()?.requestToken?.let { createSession(it) }
                    emit(UiState.Success(true))
                } else {
                    val errorResponse =
                        jsonParser.parseFromJson(
                            validateRequestTokenWithLogin.errorBody()
                                ?.string(), ErrorResponse::class.java
                        )
                    emit(UiState.Error(errorResponse.statusMessage.toString()))
                }
            } catch (e: Exception) {
                emit(UiState.Error(e.message.toString()))

            }
        }
    }

    override suspend fun getAccountDetails(sessionId: String): AccountDTO {
        return service.getAccountDetails(sessionId)
    }

    override suspend fun logout(sessionId: String): LogoutResponse {
        return service.logout(sessionId)
    }


    private suspend fun getRequestToken(): String? {
        val tokenResponse = service.getRequestToken()
        return tokenResponse.body()?.requestToken
    }

    private suspend fun createSession(requestToken: String) {
        val sessionResponse = service.createSession(requestToken).body()
        if (sessionResponse?.success == true) {
            saveSessionId(sessionResponse.sessionId.toString())
        }
    }

    private suspend fun saveSessionId(sessionId: String) {
        dataStorePreferences.writeString(Constants.SESSION_ID_KEY, sessionId)
    }

}