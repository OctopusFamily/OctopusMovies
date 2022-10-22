package com.octopus.moviesapp.ui.lists

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.octopus.moviesapp.data.repository.lists.ListsRepository
import com.octopus.moviesapp.domain.model.CreatedList
import com.octopus.moviesapp.ui.base.BaseViewModel
import com.octopus.moviesapp.util.Event
import com.octopus.moviesapp.util.UiState
import com.octopus.moviesapp.util.extensions.postEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyListsViewModel @Inject constructor(
    private val listsRepository: ListsRepository,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel(), MyListsClicksListener {

    private val args = MyListsFragmentArgs.fromSavedStateHandle(savedStateHandle)

    private val _myListsState = MutableLiveData<UiState<MutableList<CreatedList>>>(UiState.Loading)
    val myListsState: LiveData<UiState<MutableList<CreatedList>>> get() = _myListsState

    val listName = MutableLiveData("")

    private val _isNewListClicked = MutableLiveData<Event<Boolean>>()
    val isNewListClicked = _isNewListClicked

    private val _onCLickAddEvent = MutableLiveData<Event<Boolean>>()
    val onClickAddEvent = _onCLickAddEvent

    private val _isCloseClicked = MutableLiveData(Event(false))
    val isCloseClicked = _isCloseClicked

    private val _item = MutableLiveData<Event<CreatedList>>()
    val item: LiveData<Event<CreatedList>> get() = _item

    private val _isEmptyList = MutableLiveData(false)
    val isEmptyList: LiveData<Boolean>
        get() = _isEmptyList


    init {
        getData()
    }

    fun getData() {
        viewModelScope.launch {
            wrapResponse {
                listsRepository.getAllLists(0, args.sessionId).toMutableList()
            }.collectLatest {
                _myListsState.postValue(it)
            }
        }
    }

    fun onCreateList() {
        _isNewListClicked.postEvent(true)
    }

    fun onCloseDialog() {
        _isCloseClicked.postValue(Event(true))
    }

    fun onClickAddList() {
        viewModelScope.launch {
            wrapResponse {
                listsRepository.createList(args.sessionId, listName.value.toString())
            }.collectLatest {
                if (it is UiState.Success) {
                    addList(CreatedList(it.data.listId ?: 0, 0, listName.value.toString()))
                    listName.postValue(null)
                }
            }
            _onCLickAddEvent.postEvent(true)
        }
    }

    private fun addList(createdLists: CreatedList) {
        val oldList = _myListsState.value?.toData()?.toMutableList()
        oldList?.add(0, createdLists)
        _myListsState.postValue(UiState.Success(oldList!!))
    }

    fun checkIfEmptyList() {
        viewModelScope.launch {
            if (myListsState.value?.toData().isNullOrEmpty()) {
                _isEmptyList.postValue(true)
              } else {
                _isEmptyList.postValue(false)
              }
        }
    }

    override fun onListClick(item: CreatedList) {
        TODO("Not yet implemented")
    }

}
