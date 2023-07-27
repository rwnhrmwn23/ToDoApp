package com.onedev.todo.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ToDo(
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val dueDate: String = "",
): Parcelable
