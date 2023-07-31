package com.onedev.todo.presentation.list

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.onedev.todo.domain.model.ToDo
import com.onedev.todo.navigation.Screen
import org.koin.androidx.compose.koinViewModel

@Composable
fun ListScreen(
    navController: NavController,
    listViewModel: ListViewModel = koinViewModel()
) {
    val result = listViewModel.toDoList.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "You Have ${result.data.size} ToDo Today",
            color = MaterialTheme.colorScheme.primary,
            fontSize = MaterialTheme.typography.headlineLarge.fontSize,
            fontWeight = FontWeight.Bold,
            lineHeight = 36.sp
        )
        if (result.isLoading) {
            Box(contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        Box(contentAlignment = Alignment.Center) {
            result.data.let { listToDo ->
                if (listToDo.isEmpty()) {
                    Text(text = "Empty")
                } else {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(listToDo, key = { it.id }) { toDo ->
                            ListToDo(
                                data = toDo,
                                navController = navController,
                                listViewModel = listViewModel
                            )
                        }
                    }
                }
            }

            FloatingActionButton(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.BottomEnd),
                onClick = { navController.navigate(Screen.AddScreen.route) },
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add, contentDescription = "Add ToDo"
                )
            }
        }
    }
}

@Composable
fun ListToDo(
    data: ToDo,
    navController: NavController,
    listViewModel: ListViewModel
) {
    Card(
        modifier = Modifier
            .clickable {
                Log.d("ListToDo", "ListToDo: $data")
                navController.currentBackStackEntry?.savedStateHandle?.set("ToDo", data)
                navController.navigate(Screen.UpdateScreen.route)
            },
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
        ) {
            Box(modifier = Modifier.align(Alignment.CenterVertically)) {
                Checkbox(
                    checked = (data.isDone),
                    onCheckedChange = {
                        listViewModel.updateData(data, it)
                    })
            }
            Column {
                Text(
                    text = data.title,
                    fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )

                Text(
                    text = data.description,
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListToDoPreview() {
    ListToDo(
        data = ToDo(
            id = 1,
            title = "Read a BookRead a BookRead a BookRead a Book",
            description = "Psycology of Money page 30 - 40Psycology of Money page 30 - 40",
            dueDate = "31/9/2023",
            isDone = true
        ),
        navController = rememberNavController(),
        listViewModel = koinViewModel()
    )
}