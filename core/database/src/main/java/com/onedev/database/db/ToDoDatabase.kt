package com.onedev.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.onedev.database.dao.ToDoDao
import com.onedev.database.entity.ToDoEntity

@Database(entities = [ToDoEntity::class], version = 1, exportSchema = false)
abstract class ToDoDatabase : RoomDatabase() {
    abstract fun toDoDao(): ToDoDao
}