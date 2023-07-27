package com.onedev.todo.domain.usecase

import com.onedev.todo.domain.repository.ToDoRepository
import com.onedev.todo.domain.model.ToDo

class ToDouseCaseImpl(
    private val toDoRepository: ToDoRepository
) {

    fun readAllData() = toDoRepository.readAllData()

    suspend fun insertData(toDo: ToDo) = toDoRepository.insertData(toDo)

    suspend fun updateData(toDo: ToDo) =  toDoRepository.updateData(toDo)

    suspend fun deleteData(toDo: ToDo) = toDoRepository.deleteData(toDo)

    suspend fun deleteAllData() = toDoRepository.deleteAllData()
}