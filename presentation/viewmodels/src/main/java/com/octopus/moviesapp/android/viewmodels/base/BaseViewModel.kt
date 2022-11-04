package com.octopus.moviesapp.android.viewmodels.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class BaseViewModel : ViewModel() {
    protected fun <T> wrapResponse(response: suspend () -> T): Flow<UiState<T>> {
        return flow {
            emit(UiState.Loading)
            try {
                val res = response()
                emit(UiState.Success(res))
            } catch (e: Exception) {
                emit(UiState.Error(e.message.toString()))
            }
        }
    }
}