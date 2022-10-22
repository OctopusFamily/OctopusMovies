package com.octopus.moviesapp.ui.lists

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.octopus.moviesapp.data.local.DataStorePref
import com.octopus.moviesapp.data.repository.lists.ListsRepository
import com.octopus.moviesapp.domain.model.CreatedList
import com.octopus.moviesapp.ui.base.BaseViewModel
import com.octopus.moviesapp.util.Constants
import com.octopus.moviesapp.util.Event
import com.octopus.moviesapp.util.UiState
import com.octopus.moviesapp.util.extensions.postEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllListsViewModel @Inject constructor(
    private val listsRepository: ListsRepository,
    private val dataStorePref: DataStorePref
) : BaseViewModel(), CreatedListInteractionListener {

    private val _createdList = MutableLiveData<UiState<MutableList<CreatedList>?>>(UiState.Loading)
    val createdList: LiveData<UiState<MutableList<CreatedList>?>>
        get() = _createdList

    private val sessionId = getSessionId()


    val listName = MutableLiveData("")

    private val _isNewListClicked = MutableLiveData<Event<Boolean>>()
    val isNewListClicked = _isNewListClicked

    private val _onCLickAddEvent = MutableLiveData<Event<Boolean>>()
    val onClickAddEvent = _onCLickAddEvent

    private val _isCloseClicked = MutableLiveData(Event(false))
    val isCloseClicked = _isCloseClicked

    private val _item = MutableLiveData<Event<CreatedList>>()
    val item: LiveData<Event<CreatedList>>
        get() = _item

    private val _isEmptyList = MutableLiveData(false)
    val isEmptyList: LiveData<Boolean>
        get() = _isEmptyList


    init {
        getData()
    }

    fun getData() {
        viewModelScope.launch {
            wrapResponse {
                listsRepository.getAllLists(0, sessionId).toMutableList()
            }.collectLatest {
                if (it is UiState.Success) {
                    _createdList.postValue(it)
                    checkIfEmptyList()
                } else {
                    _createdList.postValue(it)
                }
            }
        }
    }

    private fun getSessionId(): String {
        var sessionId = ""
        viewModelScope.launch {
            dataStorePref.readString(Constants.SESSION_ID_KEY).collect {
                sessionId = it.toString()
            }
        }
        return sessionId
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
                listsRepository.createList(sessionId, listName.value.toString())
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
        val oldList = _createdList.value?.toData()?.toMutableList()
        oldList?.add(0, createdLists)
        _createdList.postValue(UiState.Success(oldList))
    }

    private fun checkIfEmptyList() {
        viewModelScope.launch {
            createdList.value
              if (createdList.value?.toData().isNullOrEmpty()) {
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
