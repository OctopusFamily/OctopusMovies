package com.octopus.moviesapp.ui.base

import androidx.lifecycle.ViewModel
import com.octopus.moviesapp.domain.sealed.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class BaseViewModel : ViewModel() {

    protected fun <T> wrapResponse(responseFun: suspend () -> T): Flow<UiState<T>> =
        flow {
            emit(UiState.Loading)
            try {
                val response = responseFun()
                emit(UiState.Success(response))
            } catch (e: Exception) {
                emit(UiState.Error("threw exception: ${e.message}"))
            }
        }

}