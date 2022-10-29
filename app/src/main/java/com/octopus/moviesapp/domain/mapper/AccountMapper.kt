package com.octopus.moviesapp.domain.mapper

import com.octopus.moviesapp.data.remote.response.dto.account.AccountDTO
import com.octopus.moviesapp.domain.model.Account
import com.octopus.moviesapp.util.buildImageUrl
import javax.inject.Inject

class AccountMapper @Inject constructor() : Mapper<AccountDTO,Account>() {
    override fun map(input: AccountDTO): Account {
        return Account(
            avatarPath = buildImageUrl(input.avatar?.avatarPath?.path),
            name = input.name ?: "",
            username = input.username ?: ""
        )
    }
}