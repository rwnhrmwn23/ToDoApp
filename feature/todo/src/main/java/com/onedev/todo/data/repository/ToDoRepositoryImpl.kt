package com.onedev.todo.data.repository

import com.onedev.database.source.ToDoLocalDataSource
import com.onedev.todo.domain.model.ToDo
import com.onedev.todo.data.mapper.DataMapper.mapDomainToEntity
import com.onedev.todo.data.mapper.DataMapper.mapEntitiesToDomains
import com.onedev.todo.domain.repository.ToDoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ToDoRepositoryImpl(
    private val toDoLocalDataSource: ToDoLocalDataSource
): ToDoRepository {
    override fun readAllData(date: String): Flow<List<ToDo>> =
        toDoLocalDataSource.readAllData(date).map { it.mapEntitiesToDomains() }

    override suspend fun insertData(toDo: ToDo) {
        val data = toDo.mapDomainToEntity()
        toDoLocalDataSource.insertData(data)
    }

    override suspend fun updateData(toDo: ToDo, isDone: Boolean) {
        val data = toDo.mapDomainToEntity()
        toDoLocalDataSource.updateData(data, isDone)
    }

    override suspend fun deleteData(toDo: ToDo) {
        val data = toDo.mapDomainToEntity()
        toDoLocalDataSource.deleteData(data)
    }

    override suspend fun deleteAllData() = toDoLocalDataSource.deleteAllData()
}