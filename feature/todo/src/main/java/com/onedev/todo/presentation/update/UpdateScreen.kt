package com.onedev.todo.presentation.update

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.onedev.todo.domain.model.ToDo
import com.onedev.todo.utils.toast
import org.koin.androidx.compose.koinViewModel
import java.util.Calendar
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateScreen(
    toDo: ToDo,
    navController: NavHostController,
    updateViewModel: UpdateViewModel = koinViewModel()
) {

    val oTFTitle = remember { mutableStateOf("") }
    val oTFDescription = remember { mutableStateOf("") }
    val oTFDueDate = remember { mutableStateOf("") }

    oTFTitle.value = toDo.title
    oTFDescription.value = toDo.description
    oTFDueDate.value = toDo.dueDate

    // Date Picker
    val mContext = LocalContext.current
    val mCalendar = Calendar.getInstance()

    val mYear: Int = mCalendar.get(Calendar.YEAR)
    val mMonth: Int = mCalendar.get(Calendar.MONTH)
    val mDay: Int = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()

    val mDatePickerDialog = DatePickerDialog(
        mContext, { _: DatePicker, year: Int, month: Int, mDayOfMonth: Int ->
            oTFDueDate.value = "$mDayOfMonth/${month + 1}/$year"
        }, mYear, mMonth, mDay
    )

    val openDialog = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Update ToDo") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = "Back Button"
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            openDialog.value = true
                        }
                    ) {
                        Icon(Icons.Rounded.Close, contentDescription = "Delete ToDo")
                    }
                    AlertDialog(
                        onDismissRequest = {
                            openDialog.value = false
                        },
                        title = {
                            Text(text = "Are you sure to delete this ToDo ?")
                        },
                        text = {
                            Text("ToDo will be deleted")
                        },
                        confirmButton = {
                            Button(onClick = {
                                openDialog.value = false
                                updateViewModel.deleteData(toDo)
                                mContext.toast("Delete data successful")
                            }) {
                                Text("Yes")
                            }
                        },
                        dismissButton = {
                            Button(onClick = { openDialog.value = false }) {
                                Text("No")
                            }
                        }
                    )
                }
            )
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    value = oTFTitle.value,
                    onValueChange = { text ->
                        oTFTitle.value = text
                    },
                    placeholder = { Text(text = "Title") },
                )
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    value = oTFDescription.value,
                    onValueChange = { text ->
                        oTFDescription.value = text
                    },
                    placeholder = { Text(text = "Description") },
                )
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                        .clickable {
                            mDatePickerDialog.datePicker.minDate = System.currentTimeMillis() - 1000
                            mDatePickerDialog.show()
                        },
                    value = oTFDueDate.value,
                    enabled = false,
                    leadingIcon = {
                        Icon(imageVector = Icons.Rounded.DateRange, contentDescription = "Date")
                    },
                    onValueChange = { text ->
                        oTFDueDate.value = text
                    },
                    placeholder = { Text(text = "Due Date") },
                )
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        val title = oTFTitle.value
                        val description = oTFDescription.value
                        val dueData = oTFDueDate.value
                        if (title.isEmpty() || description.isEmpty() || dueData.isEmpty()) {
                            mContext.toast("Please full all field")
                        } else {
                            val data = ToDo(
                                id = toDo.id,
                                title = title,
                                description = description,
                                dueDate = dueData,
                                isDone = toDo.isDone
                            )
                            updateViewModel.updateData(data, data.isDone)
                            mContext.toast("Update data successful")
                            navController.popBackStack()
                        }
                    }
                ) {
                    Text(text = "Update ToDo")
                }

                if (!toDo.isDone) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            updateViewModel.updateData(toDo, true)
                            mContext.toast("ToDo has done")
                            navController.popBackStack()
                        }
                    ) {
                        Text(text = "Mark as Done")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddScreenPreview() {
    UpdateScreen(ToDo(), navController = rememberNavController())
}