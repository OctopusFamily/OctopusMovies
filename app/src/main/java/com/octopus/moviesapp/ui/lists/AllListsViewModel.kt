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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
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

    private fun getSessionId(): String {
        var sessionId = ""
        viewModelScope.launch {
            dataStorePref.readString(Constants.SESSION_ID_KEY).collect {
                sessionId = it.toString()
            }
        }
        return sessionId
    }

    val listName = MutableLiveData("")

    private val _isCreateButtonClicked = MutableLiveData<Event<Boolean>>()
    val isButtonClicked = _isCreateButtonClicked

    private val _onCLickAddEvent = MutableLiveData<Event<Boolean>>()
    val onClickAddEvent = _onCLickAddEvent

    private val _item = MutableLiveData<Event<CreatedList>>()
    val item: LiveData<Event<CreatedList>>
        get() = _item

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            wrapResponse {
                Log.d("sessionId", sessionId)
                listsRepository.getAllLists(0, sessionId).toMutableList()
            }.collectLatest {
                Log.d("response :", it.toString())
                _createdList.postValue(it)
            }
        }
    }


    override fun onListClick(item: CreatedList) {
        TODO("Not yet implemented")
    }
}
