package com.onedev.todo.data.mapper

import com.onedev.database.entity.ToDoEntity
import com.onedev.todo.domain.model.ToDo

object DataMapper {
    fun List<ToDoEntity>.mapEntitiesToDomains(): List<ToDo> {
        val toDoList = ArrayList<ToDo>()
        this.map {
            val todo = ToDo(
                id = it.id,
                title = it.title,
                description = it.description,
                dueDate = it.dueDate,
                isDone = it.isDone,
            )
            toDoList.add(todo)
        }
        return toDoList
    }

    fun ToDo.mapDomainToEntity() = ToDoEntity(
        id = id,
        title = title,
        description = description,
        dueDate = dueDate,
        isDone = isDone,
    )
}