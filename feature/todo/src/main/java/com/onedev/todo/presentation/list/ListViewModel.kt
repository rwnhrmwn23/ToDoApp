package com.onedev.todo.presentation.list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onedev.todo.domain.model.ToDo
import com.onedev.todo.domain.usecase.ToDouseCaseImpl
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.Calendar

class ListViewModel(private val toDouseCaseImpl: ToDouseCaseImpl) : ViewModel() {


    private val _toDoList = mutableStateOf(ListStateHolder())
    val toDoList: State<ListStateHolder> get() = _toDoList

    init {
        val mCalendar = Calendar.getInstance().apply {  }
        val mYear: Int = mCalendar.get(Calendar.YEAR)
        val mMonth: Int = mCalendar.get(Calendar.MONTH)
        val mDay: Int = mCalendar.get(Calendar.DAY_OF_MONTH)
        val today = "$mDay/${mMonth + 1}/$mYear"
        viewModelScope.launch {
            readAllData(today)
        }
    }

    private fun readAllData(date: String) = viewModelScope.launch {
        toDouseCaseImpl.readAllData(date).onEach { listData ->
            _toDoList.value = ListStateHolder(isLoading = true)
            if (listData.isEmpty()) {
                _toDoList.value = ListStateHolder(data = emptyList())
            } else {
                _toDoList.value = ListStateHolder(data = listData)
            }
        }.launchIn(viewModelScope)
    }

    fun updateData(todo: ToDo, isDone: Boolean) {
        viewModelScope.launch {
            toDouseCaseImpl.updateData(todo, isDone)
        }
    }

    fun deleteAllData() = viewModelScope.launch {
        toDouseCaseImpl.deleteAllData()
    }
}