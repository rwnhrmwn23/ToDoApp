package com.onedev.todo.data.repository

import com.onedev.database.source.ToDoLocalDataSource
import com.onedev.todo.domain.model.ToDo
import com.onedev.todo.data.mapper.DataMapper.mapDomainToEntity
import com.onedev.todo.data.mapper.DataMapper.mapEntitiesToDomains
import com.onedev.todo.domain.repository.ToDoRepository
import kotlinx.coroutines.flow.map

class ToDoRepositoryImpl(
    private val toDoLocalDataSource: ToDoLocalDataSource
): ToDoRepository {
    override fun readAllData() =
        toDoLocalDataSource.readAllData().map { it.mapEntitiesToDomains() }

    override suspend fun insertData(toDo: ToDo) {
        val data = toDo.mapDomainToEntity()
        toDoLocalDataSource.insertData(data)
    }

    override suspend fun updateData(toDo: ToDo) {
        val data = toDo.mapDomainToEntity()
        toDoLocalDataSource.updateData(data)
    }

    override suspend fun deleteData(toDo: ToDo) {
        val data = toDo.mapDomainToEntity()
        toDoLocalDataSource.deleteData(data)
    }

    override suspend fun deleteAllData() = toDoLocalDataSource.deleteAllData()
}