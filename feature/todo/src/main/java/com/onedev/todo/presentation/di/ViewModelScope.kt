package com.onedev.todo.presentation.di

import com.onedev.todo.presentation.add.AddViewModel
import com.onedev.todo.presentation.list.ListViewModel
import com.onedev.todo.presentation.update.UpdateViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelScope = module {
    viewModel { AddViewModel(get()) }
    viewModel { ListViewModel(get()) }
    viewModel { UpdateViewModel(get()) }
}