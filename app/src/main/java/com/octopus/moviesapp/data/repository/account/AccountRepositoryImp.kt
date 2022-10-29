package com.octopus.moviesapp.data.repository.account

import com.octopus.moviesapp.data.JsonParser
import com.octopus.moviesapp.data.local.datastore.DataStorePref
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
    override fun getSessionId(): String? {
        return dataStorePref.readString(Constants.SESSION_ID_KEY).toString()
    }

    override suspend fun getRequestToken(): String? {
        return service.getRequestToken().body()?.requestToken.toString()
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

    private suspend fun createSession(requestToken: String) {
        val sessionResponse = service.createSession(requestToken).body()
        if (sessionResponse?.success == true) {
            saveSessionId(sessionResponse.sessionId.toString())
        }
    }
    override suspend fun createSessionID(requestToken: String) {
        val response = service.createSession(requestToken).body()
        if (response?.success == true){
            saveSessionId(response.sessionId.toString())
        }
     }

    private suspend fun saveSessionId(sessionId: String) {
        dataStorePref.writeString(Constants.SESSION_ID_KEY, sessionId)
    }

}