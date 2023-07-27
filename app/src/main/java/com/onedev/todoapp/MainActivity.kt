package com.onedev.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.onedev.todo.presentation.list.ListViewModel
import com.onedev.todoapp.ui.theme.ToDoAppTheme
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    val listViewModel: ListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting(viewModel = listViewModel)
                }
            }
        }
    }
}

@Composable
fun Greeting(viewModel: ListViewModel, modifier: Modifier = Modifier) {
    val result = viewModel.toDoList.value

    if (result.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        result.data.let { listToDo ->
            if (listToDo == null) {
                Text(
                    text = "Data Kosong",
                    modifier = modifier
                )
            } else {
                Text(
                    text = "Data Tersedia",
                    modifier = modifier
                )
            }

        }
    }

}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    ToDoAppTheme {
//        Greeting( null,"Android")
//    }
//}