package com.octopus.moviesapp.android.usecases.mapper

import com.octopus.moviesapp.android.remote.response.dto.account.AccountDTO
import com.octopus.moviesapp.models.model.Account
import javax.inject.Inject

class AccountMapper @Inject constructor() : Mapper<AccountDTO, Account>() {
    override fun map(input: AccountDTO): Account {
        return Account(
            avatarPath = buildImageUrl(input.avatar?.avatarPath?.path),
            name = input.name ?: "",
            username = input.username ?: ""
        )
    }
}