package com.onedev.todo.presentation.update

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onedev.todo.domain.model.ToDo
import com.onedev.todo.domain.usecase.ToDouseCaseImpl
import kotlinx.coroutines.launch

class UpdateViewModel(private val toDouseCaseImpl: ToDouseCaseImpl): ViewModel() {

    fun updateData(todo: ToDo) {
        viewModelScope.launch {
            toDouseCaseImpl.updateData(todo)
        }
    }

    fun deleteData(todo: ToDo) {
        viewModelScope.launch {
            toDouseCaseImpl.deleteData(todo)
        }
    }
}