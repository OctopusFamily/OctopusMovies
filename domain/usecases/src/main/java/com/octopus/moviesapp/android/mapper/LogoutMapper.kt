package com.octopus.moviesapp.android.mapper

import com.octopus.moviesapp.android.remote.response.LogoutResponse
import com.octopus.moviesapp.models.model.Logout
import javax.inject.Inject

class LogoutMapper @Inject constructor() : Mapper<LogoutResponse, Logout>() {
    override fun map(input: LogoutResponse): Logout {
        return Logout(
            loggedOut = input.success ?: false
        )
    }
}