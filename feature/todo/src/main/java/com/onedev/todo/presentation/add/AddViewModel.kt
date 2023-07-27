package com.onedev.todo.presentation.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onedev.todo.domain.model.ToDo
import com.onedev.todo.domain.usecase.ToDouseCaseImpl
import kotlinx.coroutines.launch

class AddViewModel(private val toDouseCaseImpl: ToDouseCaseImpl): ViewModel() {

    fun insertData(todo: ToDo) {
        viewModelScope.launch {
            toDouseCaseImpl.insertData(todo)
        }
    }
}