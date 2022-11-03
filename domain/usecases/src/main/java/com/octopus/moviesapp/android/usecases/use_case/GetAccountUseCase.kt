package com.octopus.moviesapp.android.usecases.use_case

import android.accounts.Account
import com.octopus.moviesapp.android.usecases.mapper.AccountMapper
import com.octopus.moviesapp.repositories.repository.account.AccountRepository
import javax.inject.Inject

class GetAccountUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    private val accountMapper: AccountMapper
) {
    suspend operator fun invoke(sessionId: String): Account {
        return accountMapper.map(accountRepository.getAccountDetails(sessionId))
    }
}