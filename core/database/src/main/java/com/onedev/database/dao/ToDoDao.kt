package com.onedev.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.onedev.database.entity.ToDoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {

    @Query("SELECT * FROM tb_todo ORDER BY id ASC")
    fun readAllData(): Flow<List<ToDoEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(toDoEntity: ToDoEntity)

    @Update
    suspend fun updateData(toDoEntity: ToDoEntity)

    @Delete
    suspend fun deleteData(toDoEntity: ToDoEntity)

    @Query("DELETE FROM tb_todo")
    suspend fun deleteAllData()

}