package com.octopus.moviesapp.data.repository.account

import com.octopus.moviesapp.data.local.datastore.DataStorePref
import com.octopus.moviesapp.data.remote.response.login.RequestTokenResponse
import com.octopus.moviesapp.data.remote.service.TMDBApiService
import com.octopus.moviesapp.util.Constants
import retrofit2.Response
import javax.inject.Inject


class AccountRepositoryImp @Inject constructor(
    private val service: TMDBApiService,
    private val dataStorePref: DataStorePref,
) : AccountRepository {
    override fun getSessionId(): String? {
        return dataStorePref.readString(Constants.SESSION_ID_KEY).toString()
    }

    override suspend fun getRequestToken(): String? {
        return service.getRequestToken().body()?.requestToken.toString()
     }

    override suspend fun validateRequestTokenWithLogin(body: Map<String, Any>): Response<RequestTokenResponse> {
        return service.validateRequestTokenWithLogin(body)
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