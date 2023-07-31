package com.onedev.todo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.onedev.todo.domain.model.ToDo
import com.onedev.todo.presentation.add.AddScreen
import com.onedev.todo.presentation.list.ListScreen
import com.onedev.todo.presentation.update.UpdateScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController,
) {

    NavHost(navController = navController, startDestination = Screen.ListScreen.route) {
        composable(route = Screen.ListScreen.route) {
            ListScreen(navController)
        }
        composable(route = Screen.AddScreen.route) {
            AddScreen(navController)
        }
        composable(route = Screen.UpdateScreen.route) {
            val toDo = navController.previousBackStackEntry?.savedStateHandle?.get<ToDo>("ToDo")
            toDo?.let {
                UpdateScreen(it, navController)
            }
        }
    }
}