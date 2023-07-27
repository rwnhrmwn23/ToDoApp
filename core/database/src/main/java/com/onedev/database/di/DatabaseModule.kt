package com.onedev.database.di

import androidx.room.Room
import com.onedev.database.db.ToDoDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    factory { get<ToDoDatabase>().toDoDao() }
    single {
        Room.databaseBuilder(androidContext(), ToDoDatabase::class.java, "ToDoDatabase")
            .fallbackToDestructiveMigration()
            .build()
    }
}