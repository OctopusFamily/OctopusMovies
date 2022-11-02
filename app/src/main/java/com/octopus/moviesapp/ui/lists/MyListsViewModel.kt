package com.octopus.moviesapp.ui.lists

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.octopus.moviesapp.domain.use_case.lists_use_case.CreateListUseCase
import com.octopus.moviesapp.domain.use_case.lists_use_case.GetCreatedListsUseCase
import com.octopus.moviesapp.ui.base.BaseViewModel
import com.octopus.moviesapp.ui.lists.listsUIState.CreateListUIState
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
class MyListsViewModel @Inject constructor(
    private val createListUseCase: CreateListUseCase,
    private val getCreatedListsUseCase: GetCreatedListsUseCase,
    private val createdListsUIMapper: CreatedListsUIMapper,
) : BaseViewModel(), MyListsClicksListener {


    private val _createdListsUIState = MutableStateFlow(ListsUIState())
    val createdListsUIState: StateFlow<ListsUIState> get() = _createdListsUIState

    private val _listName = MutableStateFlow(CreateListUIState())

    private val _isNewListClicked = MutableLiveData<Event<Boolean>>()
    val isNewListClicked = _isNewListClicked

    private val _onCLickAddEvent = MutableLiveData<Event<Boolean>>()
    val onClickAddEvent = _onCLickAddEvent

    private val _isCloseClicked = MutableLiveData(Event(false))
    val isCloseClicked = _isCloseClicked

    private val _toastErrorMessage = MutableLiveData<Event<Boolean>>()
    val toastErrorMessage = _toastErrorMessage

    private val _item = MutableLiveData<Event<CreatedListsUIState>>()
    val item: LiveData<Event<CreatedListsUIState>> get() = _item

    private val _isArrowBackClicked = MutableLiveData(Event(false))
    val isArrowBackClicked = _isArrowBackClicked

    init {
        getData()
    }

    fun getData() {
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

    fun getListName(listName: CharSequence) {
        _listName.update { it.copy(listName = listName.toString()) }
    }

    fun onCreateList() {
        _isNewListClicked.postEvent(true)
    }

    fun onCloseDialog() {
        _isCloseClicked.postValue(Event(true))
    }

    fun onClickAddList() {
        viewModelScope.launch {
            try {
                _createdListsUIState.update {
                    it.copy(
                        isLoading = false,
                        createdLists = createListUseCase(listName = _listName.value.listName).map {
                            createdListsUIMapper.map(it)
                        },
                        isFailure = false,
                        isEmpty = false,
                        error = ""
                    )
                }
            } catch (e: Throwable) {
                _toastErrorMessage.postEvent(true)
                _createdListsUIState.update { it.copy(error = e.message.toString()) }
            }
            _listName.update { it.copy(listName = "") }
            _onCLickAddEvent.postEvent(true)
        }
    }

    fun retry() {
        _createdListsUIState.update {
            it.copy(
                isLoading = true,
                isFailure = false,
                isSuccess = false,
                createdLists = emptyList()
            )
        }
        getData()
    }

    fun onNavigateBackClick(){
        _isArrowBackClicked.postValue(Event(true))
    }

    override fun onListClick(item: CreatedListsUIState) {
        _item.postValue(Event(item))
    }

}
