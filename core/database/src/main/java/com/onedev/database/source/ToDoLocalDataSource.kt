package com.onedev.database.source

import com.onedev.database.dao.ToDoDao
import com.onedev.database.entity.ToDoEntity

class ToDoLocalDataSource(private val toDoDao: ToDoDao) {

    fun readAllData() = toDoDao.readAllData()

    suspend fun insertData(toDoEntity: ToDoEntity) = toDoDao.insertData(toDoEntity)

    suspend fun updateData(toDoEntity: ToDoEntity, isDone: Boolean) {
        toDoEntity.isDone = isDone
        toDoDao.updateData(toDoEntity)
    }

    suspend fun deleteData(toDoEntity: ToDoEntity) = toDoDao.deleteData(toDoEntity)

    suspend fun deleteAllData() = toDoDao.deleteAllData()
}