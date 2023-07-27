package com.onedev.todo.presentation.list

import com.onedev.todo.domain.model.ToDo

data class ListStateHolder(
    val isLoading: Boolean = false,
    val data: List<ToDo>? = null,
    val error: String = ""
)