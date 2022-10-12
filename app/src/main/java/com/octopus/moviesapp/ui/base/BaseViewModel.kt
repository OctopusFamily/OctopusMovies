package com.octopus.moviesapp.ui.base

import android.util.Log
import androidx.lifecycle.ViewModel
import com.octopus.moviesapp.domain.sealed.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class BaseViewModel : ViewModel() {
    protected fun <T> wrapResponse(response: suspend () -> T): Flow<UiState<T>> {
        Log.d("tests", "Wraper")
        return flow {
            emit(UiState.Loading)
            try {
                Log.d("tests", "Inside Try")
                val res = response()
                Log.d("tests", "res: $res")
                emit(UiState.Success(res))
            } catch (e: Exception) {
                Log.d("tests", "error: ${e.message}")
                emit(UiState.Error(e.message.toString()))
            }
        }
    }
}