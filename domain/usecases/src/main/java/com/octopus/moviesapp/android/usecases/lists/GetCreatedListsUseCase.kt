package com.octopus.moviesapp.android.usecases.lists_use_case

import com.octopus.moviesapp.android.usecases.mapper.CreatedListMapper
import com.octopus.moviesapp.models.model.CreatedList
import com.octopus.moviesapp.repositories.repository.account.AccountRepository
import com.octopus.moviesapp.repositories.repository.lists.ListsRepository
import javax.inject.Inject

class GetCreatedListsUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    private val listsRepository: ListsRepository,
    private val createdListMapper: CreatedListMapper
) {
    suspend operator fun invoke() : List<CreatedList>{
        val sessionID = accountRepository.getSessionId()
        return sessionID.let{
            val response = listsRepository.getAllLists(0,sessionID.toString())
            response.let {
                createdListMapper.map(it)
            } ?: throw Throwable("Unable to get created lists")
        }
    }
}