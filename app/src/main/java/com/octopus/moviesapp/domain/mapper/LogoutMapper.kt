package com.octopus.moviesapp.domain.mapper

import com.octopus.moviesapp.data.remote.response.LogoutResponse
import com.octopus.moviesapp.domain.model.Logout
import javax.inject.Inject

class LogoutMapper @Inject constructor() : Mapper<LogoutResponse, Logout>() {
    override fun map(input: LogoutResponse): Logout {
        return Logout(
            loggedOut = input.success ?: false
        )
    }
}