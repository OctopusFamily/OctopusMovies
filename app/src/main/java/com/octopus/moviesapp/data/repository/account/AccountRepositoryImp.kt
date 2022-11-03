package com.octopus.moviesapp.data.repository.account

import com.octopus.moviesapp.data.JsonParser
import com.octopus.moviesapp.data.local.datastore.DataStorePreferences
import com.octopus.moviesapp.data.remote.response.LogoutResponse
import com.octopus.moviesapp.data.remote.response.dto.account.AccountDTO
import com.octopus.moviesapp.data.remote.response.login.ErrorResponse
import com.octopus.moviesapp.data.remote.response.login.RequestTokenResponse
import com.octopus.moviesapp.data.remote.service.TMDBApiService
import com.octopus.moviesapp.util.Constants
import retrofit2.Response
import javax.inject.Inject


class AccountRepositoryImp @Inject constructor(
    private val service: TMDBApiService,
    private val dataStorePreferences: DataStorePreferences,
    private val jsonParser: JsonParser,
) : AccountRepository {
    override fun getSessionId(): String? {
        return dataStorePreferences.readString(Constants.SESSION_ID_KEY)
    }

    override suspend fun login(userName: String, password: String): Boolean {
        return try {
            val token = getRequestToken().toString()
            val body = mapOf(
                "username" to userName,
                "password" to password,
                "request_token" to token,
            ).toMap()
            val validateRequestTokenWithLogin = service.validateRequestTokenWithLogin(body)
            if (validateRequestTokenWithLogin.isSuccessful) {
                validateRequestTokenWithLogin.body()?.requestToken?.let { createSessionID(it) }
                true
            } else {
                val errorResponse = jsonParser.parseFromJson(
                    validateRequestTokenWithLogin.errorBody()?.string(),
                    ErrorResponse::class.java
                )
                throw Throwable(errorResponse.statusMessage)
            }
        } catch (e: Exception) {
            throw Throwable(e)
        }
    }

    override suspend fun getAccountDetails(sessionId: String): AccountDTO {
        return service.getAccountDetails(sessionId)
    }

    override suspend fun validateRequestTokenWithLogin(body: Map<String, Any>): Response<RequestTokenResponse> {
        return service.validateRequestTokenWithLogin(body)
    }

    override suspend fun logout(sessionId: String): LogoutResponse {
        return service.logout(sessionId)
    }


    override suspend fun getRequestToken(): String? {
        return service.getRequestToken().body()?.requestToken.toString()
    }

    override suspend fun createSessionID(requestToken: String) {
        val response = service.createSession(requestToken).body()
        if (response?.success == true) {
            saveSessionId(response.sessionId.toString())
        }
    }

    private suspend fun saveSessionId(sessionId: String) {
        dataStorePreferences.writeString(Constants.SESSION_ID_KEY, sessionId)
    }

}