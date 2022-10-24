package com.octopus.moviesapp.ui.base

import android.util.Log
import androidx.lifecycle.ViewModel
import com.octopus.moviesapp.util.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

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

    protected fun <T> wrapResponse2(response: suspend () -> Response<T>): Flow<UiState<T>> {
        return flow {
            emit(UiState.Loading)
            try {
                val res = response()
                if (res.isSuccessful){
                    emit(UiState.Success(res.body()!!))
                }else{
                    emit(UiState.Error(res.message()))
                }

            } catch (e: Exception) {
                emit(UiState.Error(e.message.toString()))
                Log.e("WShamardn","state is: ${e.message}")
            }
        }
    }
}