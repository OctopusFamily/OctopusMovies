package com.octopus.moviesapp.ui.lists

import androidx.lifecycle.*
import com.octopus.moviesapp.domain.use_case.lists_use_case.GetListDetailsUseCase
import com.octopus.moviesapp.ui.base.BaseViewModel
import com.octopus.moviesapp.ui.lists.listsUIState.GetListDetailsUIState
import com.octopus.moviesapp.ui.lists.listsUIState.ListDetailsUIState
import com.octopus.moviesapp.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ListDetailsViewModel @Inject constructor(
    private val getListDetailsUseCase: GetListDetailsUseCase,
    private val listDetailsUIMapper: ListDetailsUIMapper,
    saveStateHandle: SavedStateHandle
) : BaseViewModel(), ListDetailsInteractionListener {

    private val args = ListDetailsFragmentArgs.fromSavedStateHandle(saveStateHandle)

    private val _listDetailsUIState = MutableStateFlow(GetListDetailsUIState())
    val listDetailsUIState: StateFlow<GetListDetailsUIState> get() = _listDetailsUIState

    private val _item = MutableLiveData<Event<ListDetailsUIState>>()
    val item: LiveData<Event<ListDetailsUIState>> get() = _item

    private val _listName = args.listName
    val listName: String get() = _listName

    private val _isArrowBackClicked = MutableLiveData(Event(false))
    val isArrowBackClicked = _isArrowBackClicked

    init {
        getListDetailsById(args.listId)
    }

    private fun getListDetailsById(id: Int) {
        viewModelScope.launch {
            try {
                val listDetails = getListDetailsUseCase(id).map {
                    listDetailsUIMapper.map(it)
                }
                _listDetailsUIState.update {
                    it.copy(
                        isLoading = false,
                        isEmpty = listDetails.isEmpty(),
                        isSuccess = true,
                        isFailure = false,
                        listDetails = listDetails
                    )
                }
            } catch (e: Throwable) {
                _listDetailsUIState.update {
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

    fun getData() {
        _listDetailsUIState.update {
            it.copy(
                isLoading = true,
                isFailure = false,
                isSuccess = false,
                listDetails = emptyList()
            )
        }
        getListDetailsById(args.listId)
    }

    fun onNavigateBackClick() {
        _isArrowBackClicked.postValue(Event(true))
    }

    override fun onItemClick(item: ListDetailsUIState) {
        _item.postValue(Event(item))
    }

}

