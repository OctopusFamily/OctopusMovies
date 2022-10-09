package com.octopus.moviesapp.ui.base

import android.util.Log
import androidx.lifecycle.ViewModel
import com.octopus.moviesapp.domain.sealed.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class BaseViewModel : ViewModel() {
    protected fun <T> wrapResponse(response: suspend () -> T): Flow<UiState<T>> {
        return flow {
            emit(UiState.Loading)
            try {
                val res = response()
                Log.d("MALT", "res: $res")
                emit(UiState.Success(res))
            } catch (e: Exception) {
                Log.d("MALT", "error: ${e.message}")
                emit(UiState.Error(e.message.toString()))
            }
        }
    }
}