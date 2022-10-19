package com.octopus.moviesapp.data.repository


import com.octopus.moviesapp.data.DataClassParser
import com.octopus.moviesapp.data.local.DataStorePref
import com.octopus.moviesapp.data.remote.response.login.ErrorResponse
import com.octopus.moviesapp.data.remote.service.TMDBApiService
import com.octopus.moviesapp.util.DataStorePreferencesKeys
import com.octopus.moviesapp.util.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class AccountRepositoryImp @Inject constructor(
    private val service: TMDBApiService,
    private val dataStorePref: DataStorePref,
    private val dataClassParser: DataClassParser,
) : AccountRepository {
    override fun getSessionId(): Flow<String?> {
        return dataStorePref.readString(DataStorePreferencesKeys.SESSION_ID_KEY)
    }
    override suspend fun login(
        userName: String,
        password: String,
    ): Flow<UiState<Boolean>> {
        return flow {
            emit(UiState.Loading)
            try {
                val token = getRequestToken().toString()
                val body = mapOf<String, Any>(
                    "username" to userName,
                    "password" to password,
                    "request_token" to token,
                ).toMap()
                val validateRequestTokenWithLogin = service.validateRequestTokenWithLogin(body)
                if (validateRequestTokenWithLogin.isSuccessful) {
                    validateRequestTokenWithLogin.body()?.requestToken?.let { createSession(it) }
                    emit(UiState.Success(true))
                } else {
                    val errorResponse =
                        dataClassParser.parseFromJson(validateRequestTokenWithLogin.errorBody()
                            ?.string(), ErrorResponse::class.java)
                    emit(UiState.Error(errorResponse.statusMessage.toString()))
                }
            } catch (e: Exception) {
                emit(UiState.Error(e.message.toString()))

            }
        }
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
        dataStorePref.writeString(DataStorePreferencesKeys.SESSION_ID_KEY, sessionId)
    }

}