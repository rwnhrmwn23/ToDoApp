package com.onedev.todoapp

import android.app.Application
import com.onedev.database.di.databaseModule
import com.onedev.todo.domain.di.repositoryModule
import com.onedev.todo.domain.di.useCaseModule
import com.onedev.todo.presentation.di.viewModelScope
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelScope,
                )
            )
        }
    }

}