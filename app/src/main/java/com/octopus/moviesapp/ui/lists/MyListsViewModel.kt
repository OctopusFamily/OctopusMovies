package com.octopus.moviesapp.ui.lists

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.octopus.moviesapp.data.local.DataStorePref
import com.octopus.moviesapp.data.repository.lists.ListsRepository
import com.octopus.moviesapp.domain.model.CreatedList
import com.octopus.moviesapp.ui.base.BaseViewModel
import com.octopus.moviesapp.util.Constants
import com.octopus.moviesapp.util.Event
import com.octopus.moviesapp.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyListsViewModel @Inject constructor(
    private val listsRepository: ListsRepository,
    private val dataStorePref: DataStorePref,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel(), MyListsClicksListener {

    private val args = MyListsFragmentArgs.fromSavedStateHandle(savedStateHandle)

    private val _myListsState = MutableLiveData<UiState<MutableList<CreatedList>>>(UiState.Loading)
    val myListsState: LiveData<UiState<MutableList<CreatedList>>> get() = _myListsState

    val listName = MutableLiveData("")

    private val _isCreateButtonClicked = MutableLiveData<Event<Boolean>>()
    val isButtonClicked = _isCreateButtonClicked

    private val _onCLickAddEvent = MutableLiveData<Event<Boolean>>()
    val onClickAddEvent = _onCLickAddEvent

    private val _item = MutableLiveData<Event<CreatedList>>()
    val item: LiveData<Event<CreatedList>> get() = _item

    init {
        getData()
    }


    override fun onListClick(item: CreatedList) {
        TODO("Not yet implemented")
    }

    private fun getData() {
        viewModelScope.launch {
            wrapResponse { listsRepository.getAllLists(0, args.sessionId).toMutableList() }.collectLatest {
                _myListsState.postValue(it)
            }
        }
    }
}
