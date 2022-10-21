package com.octopus.moviesapp.ui.lists

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.octopus.moviesapp.data.local.DataStorePref
import com.octopus.moviesapp.data.repository.ListsRepository
import com.octopus.moviesapp.domain.model.CreatedList
import com.octopus.moviesapp.ui.base.BaseViewModel
import com.octopus.moviesapp.util.Constants
import com.octopus.moviesapp.util.Event
import com.octopus.moviesapp.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllListsViewModel @Inject constructor(
    private val listsRepository: ListsRepository,
    private val dataStorePref: DataStorePref
) : BaseViewModel() , CreatedListInteractionListener {

    private val _createdList = MutableLiveData<UiState<MutableList<CreatedList>?>>()
    val createdList = _createdList

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

    fun getData() {
        wrapResponse {
            viewModelScope.launch {
                dataStorePref.readString(Constants.SESSION_ID_KEY).collect {
                    _createdList.postValue(UiState.Loading)
                    val response = listsRepository.getAllLists(0, it.toString()).toMutableList()
                    _createdList.postValue(UiState.Success(response))
                }
            }
        }
    }

    override fun onListClick(item: CreatedList) {
        TODO("Not yet implemented")
    }
}
