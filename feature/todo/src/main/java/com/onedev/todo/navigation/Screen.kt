package com.onedev.todo.navigation

sealed class Screen(val route: String) {
    object ListScreen : Screen(route = "list_screen")
    object AddScreen : Screen(route = "add_screen")
    object UpdateScreen : Screen(route = "update_screen")
}
