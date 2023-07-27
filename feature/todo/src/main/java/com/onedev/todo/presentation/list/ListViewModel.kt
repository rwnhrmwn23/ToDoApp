package com.onedev.todo.presentation.list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onedev.todo.domain.usecase.ToDouseCaseImpl
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class ListViewModel(private val toDouseCaseImpl: ToDouseCaseImpl) : ViewModel() {


    private val _toDoList = mutableStateOf(ListStateHolder())
    val toDoList: State<ListStateHolder> get() = _toDoList

    init {
        viewModelScope.launch {
            readAllData()
        }
    }

    private fun readAllData() = viewModelScope.launch {
        toDouseCaseImpl.readAllData().onEach { listData ->
            _toDoList.value = ListStateHolder(isLoading = true)
            if (listData.isEmpty()) {
                _toDoList.value = ListStateHolder(data = null)
            } else {
                _toDoList.value = ListStateHolder(data = listData)
            }
        }.launchIn(viewModelScope)
    }

    fun deleteAllData() = viewModelScope.launch {
        toDouseCaseImpl.deleteAllData()
    }
}