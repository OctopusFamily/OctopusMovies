package com.octopus.moviesapp.data.repository.account


import android.util.Log
import com.octopus.moviesapp.data.JsonParser
import com.octopus.moviesapp.data.local.DataStorePref
import com.octopus.moviesapp.data.remote.response.login.ErrorResponse
import com.octopus.moviesapp.data.remote.service.TMDBApiService
import com.octopus.moviesapp.domain.mapper.AccountMapper
import com.octopus.moviesapp.domain.model.Account
import com.octopus.moviesapp.util.Constants
import com.octopus.moviesapp.util.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class AccountRepositoryImp @Inject constructor(
    private val service: TMDBApiService,
    private val dataStorePref: DataStorePref,
    private val jsonParser: JsonParser,
    private val accountMapper: AccountMapper
) : AccountRepository {
    override fun getSessionId(): Flow<String?> {
        return dataStorePref.readString(Constants.SESSION_ID_KEY)
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

    override suspend fun getAccountDetails(sessionId: String): Account {
        return accountMapper.map(service.getAccountDetails(sessionId))
    }

    override suspend fun logout(): Flow<UiState<Boolean>> {
        return flow {
            emit(UiState.Loading)
            try {
                getSessionId().collect {
                    val logout = service.logout(it.toString())
                    if (logout.isSuccessful) {
                        dataStorePref.writeString(Constants.SESSION_ID_KEY, "")
                        Log.d("logout", "MyRepo${it.toString()}")
                        emit(UiState.Success(true))
                    } else {
                        emit(UiState.Error("There is an error"))
                    }
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
        dataStorePref.writeString(Constants.SESSION_ID_KEY, sessionId)
    }

}