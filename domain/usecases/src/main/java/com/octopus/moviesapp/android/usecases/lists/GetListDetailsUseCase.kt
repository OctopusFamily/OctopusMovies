package com.octopus.moviesapp.android.usecases.lists_use_case

import com.octopus.moviesapp.android.usecases.mapper.ListDetailsMapper
import com.octopus.moviesapp.models.model.ListDetails
import com.octopus.moviesapp.repositories.repository.lists.ListsRepository
import javax.inject.Inject

class GetListDetailsUseCase @Inject constructor(
    private val listsRepository: ListsRepository,
    private val listDetailsMapper: ListDetailsMapper
){
    suspend operator fun invoke(listID : Int): List<ListDetails>{
        val listDetails = listsRepository.getListDetails(listID)
        return listDetails.let { listDetails ->
            listDetailsMapper.map(listDetails)
        } ?: throw Throwable("Unable to get list content")
    }
}