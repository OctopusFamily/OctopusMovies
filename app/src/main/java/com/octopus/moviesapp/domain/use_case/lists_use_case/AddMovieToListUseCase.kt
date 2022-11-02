package com.octopus.moviesapp.domain.use_case.lists_use_case

import com.octopus.moviesapp.data.repository.account.AccountRepository
import com.octopus.moviesapp.data.repository.lists.ListsRepository
import javax.inject.Inject

class AddMovieToListUseCase @Inject constructor(
    private val listsRepository: ListsRepository,
    private val accountRepository: AccountRepository
) {
    suspend operator fun invoke(listID: Int, mvoieID: Int) : String  {
        val sessionID = accountRepository.getSessionId()
        return sessionID.let {
            listsRepository.addMovieToList(it.toString(),listID,mvoieID).toString()
            "tha movie was added successfully"
        } ?: throw Throwable("Unable to save the movie")
    }
}