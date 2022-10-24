package com.octopus.moviesapp.ui.lists

import android.util.Log
import androidx.lifecycle.*
import com.octopus.moviesapp.data.repository.lists.ListsRepository
import com.octopus.moviesapp.domain.model.ListDetails
import com.octopus.moviesapp.ui.base.BaseViewModel
import com.octopus.moviesapp.util.Event
import com.octopus.moviesapp.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.E


@HiltViewModel
class ListDetailsViewModel @Inject constructor(
    private val listsRepository: ListsRepository,
    saveStateHandle: SavedStateHandle
) : BaseViewModel(), ListDetailsInteractionListener {

    private val args = ListDetailsFragmentArgs.fromSavedStateHandle(saveStateHandle)

   private val _listDetails = MutableLiveData<UiState<List<ListDetails>>>(UiState.Loading)
    val listDetails : LiveData<UiState<List<ListDetails>>> get() = _listDetails

    private val _itemId = MutableLiveData<Event<Int>>()
    val itemId: LiveData<Event<Int>>
        get() = _itemId

    private val _listName = args.listName
    val listName: String
        get() = _listName

    private val _isEmptyList = MutableLiveData(false)
    val isEmptyList: LiveData<Boolean>
        get() = _isEmptyList

    private val _isArrowBackClicked = MutableLiveData(Event(false))
    val isArrowBackClicked = _isArrowBackClicked

    init {
          getListDetailsById(args.listId)
    }

    private fun getListDetailsById(id: Int) {
        viewModelScope.launch {
            wrapResponse {
                 listsRepository.getListDetails(id)
            }.collectLatest {
                _listDetails.postValue(it)
            }
        }
    }

    fun getData() {
        getListDetailsById(args.listId)
    }

    fun onNavigateBackClick(){
        _isArrowBackClicked.postValue(Event(true))
    }

    fun setEmptyListAnimation(isEmpty : Boolean){
        _isEmptyList.postValue(isEmpty)
    }

    override fun onItemClick(item: ListDetails) {
        _itemId.postValue(Event(item.id))
    }

}

