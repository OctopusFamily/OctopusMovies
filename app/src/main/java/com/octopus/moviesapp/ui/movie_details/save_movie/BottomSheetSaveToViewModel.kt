package com.octopus.moviesapp.ui.movie_details.save_movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.octopus.moviesapp.MyApplication
import com.octopus.moviesapp.domain.use_case.lists_use_case.AddMovieToListUseCase
import com.octopus.moviesapp.domain.use_case.lists_use_case.GetCreatedListsUseCase
import com.octopus.moviesapp.ui.base.BaseViewModel
import com.octopus.moviesapp.ui.lists.CreatedListsUIMapper
import com.octopus.moviesapp.ui.lists.MyListsClicksListener
import com.octopus.moviesapp.ui.lists.listsUIState.CreatedListsUIState
import com.octopus.moviesapp.ui.lists.listsUIState.ListsUIState
import com.octopus.moviesapp.util.Event
import com.octopus.moviesapp.util.extensions.postEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BottomSheetSaveToViewModel @Inject constructor(
    private val addMovieToListUseCase: AddMovieToListUseCase,
    private val getCreatedListsUseCase: GetCreatedListsUseCase,
    private val createdListsUIMapper: CreatedListsUIMapper,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel(), MyListsClicksListener {

    private val args = BottomSheetSaveToArgs.fromSavedStateHandle(savedStateHandle)

    private val _createdListsUIState = MutableStateFlow(ListsUIState())
    val createdListsUIState: StateFlow<ListsUIState> get() = _createdListsUIState

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage = _toastMessage

    private val _isNewListClicked = MutableLiveData<Event<Boolean>>()
    val isNewListClicked = _isNewListClicked

    val sessionID = MyApplication.sessionId

    init {
        getCreatedLists()
    }

    private fun getCreatedLists() {
        viewModelScope.launch {
            try {
                val lists = getCreatedListsUseCase().map {
                    createdListsUIMapper.map(it)
                }
                _createdListsUIState.update {
                    it.copy(
                        isLoading = false,
                        isEmpty = lists.isEmpty(),
                        isSuccess = true,
                        isFailure = false,
                        createdLists = lists
                    )
                }
            } catch (e: Throwable) {
                _createdListsUIState.update {
                    it.copy(
                        isLoading = false,
                        isSuccess = false,
                        isFailure = true,
                        error = e.message.toString()
                    )
                }
            }
        }
    }
    override fun onListClick(item: CreatedListsUIState) {
        viewModelScope.launch {
            try {
                val response = addMovieToListUseCase(item.listID,args.movieID)
                _toastMessage.postValue(response)
            } catch (e : Throwable){
                _toastMessage.postValue(e.message.toString())
            }
        }
     }
    fun navigateToMyLists(){
        _isNewListClicked.postEvent(true)
    }
}