package com.onedev.todo.domain.repository

import com.onedev.todo.domain.model.ToDo
import kotlinx.coroutines.flow.Flow

interface ToDoRepository {
    fun readAllData(): Flow<List<ToDo>>

    suspend fun insertData(toDo: ToDo)

    suspend fun updateData(toDo: ToDo, isDone: Boolean)

    suspend fun deleteData(toDo: ToDo)

    suspend fun deleteAllData()
}