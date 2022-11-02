package com.octopus.moviesapp.domain.use_case.lists_use_case

import com.octopus.moviesapp.data.repository.account.AccountRepository
import com.octopus.moviesapp.data.repository.lists.ListsRepository
import com.octopus.moviesapp.domain.mapper.CreatedListMapper
import com.octopus.moviesapp.domain.model.CreatedList
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