package com.onedev.todo.domain.di

import com.onedev.database.source.ToDoLocalDataSource
import com.onedev.todo.data.repository.ToDoRepositoryImpl
import com.onedev.todo.domain.repository.ToDoRepository
import com.onedev.todo.domain.usecase.ToDouseCaseImpl
import org.koin.dsl.module

val repositoryModule = module {
    single { ToDoLocalDataSource(get()) }
    single<ToDoRepository> {
        ToDoRepositoryImpl(get())
    }
}

val useCaseModule = module {
    single { ToDouseCaseImpl(get()) }
}