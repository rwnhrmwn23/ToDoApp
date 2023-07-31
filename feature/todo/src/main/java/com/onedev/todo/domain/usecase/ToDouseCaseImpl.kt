package com.onedev.todo.domain.usecase

import com.onedev.todo.domain.repository.ToDoRepository
import com.onedev.todo.domain.model.ToDo

class ToDouseCaseImpl(
    private val toDoRepository: ToDoRepository
) {

    fun readAllData(date: String) = toDoRepository.readAllData(date)

    suspend fun insertData(toDo: ToDo) = toDoRepository.insertData(toDo)

    suspend fun updateData(toDo: ToDo, isDone: Boolean) =  toDoRepository.updateData(toDo, isDone)

    suspend fun deleteData(toDo: ToDo) = toDoRepository.deleteData(toDo)

    suspend fun deleteAllData() = toDoRepository.deleteAllData()
}