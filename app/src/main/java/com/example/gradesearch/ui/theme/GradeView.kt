package com.example.gradesearch.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gradesearch.data.Result


@Composable
fun GradeViewerScreen(navController: NavHostController, gradeViewModel: GradeViewModel = viewModel()) {
    val studentName = "John Doe"
    val matricNumber = "A1234567"

    val gradeItems by gradeViewModel.grades.collectAsState()
    val selectedGrades = remember { mutableStateListOf<Result>() }
    var selectMode by remember { mutableStateOf(false) } // false for viewing, true for selecting
    var showDialog by remember { mutableStateOf(false) } // State to manage dialog visibility

    LaunchedEffect(Unit) {
        gradeViewModel.loadGrades()
    }

    if (showDialog) {
        DeleteConfirmationDialog(
            onConfirm = {
                //gradeViewModel.deleteGrades(selectedGrades)
                selectedGrades.clear()
                showDialog = false
                selectMode = false
            },
            onDismiss = {
                showDialog = false
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = { navController.popBackStack() },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Text("Back", color = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Grades for $studentName", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Matric Number: $matricNumber")
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = { navController.navigate("GradeEntry") }, modifier = Modifier.padding(end = 8.dp)) {
                Text("Add Grade")
            }
            Button(onClick = {
                selectMode = !selectMode
                if (!selectMode) {
                    selectedGrades.clear()
                }
            }) {
                Text(if (selectMode) "Cancel" else "Select Grade")
            }
            Spacer(modifier = Modifier.weight(1f)) // Pushes the delete button to the end
            if (selectMode) {
                Button(onClick = {
                    showDialog = true
                },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                ) {
                    Text("Delete")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(gradeItems) { grade ->
                GradeItemView(grade, selectedGrades.contains(grade), selectMode) {
                    if (selectMode) {
                        if (selectedGrades.contains(grade)) {
                            selectedGrades.remove(grade)
                        } else {
                            selectedGrades.add(grade)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DeleteConfirmationDialog(onConfirm: () -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Confirm Delete") },
        text = { Text(text = "Are you sure you want to delete the selected grades?") },
        confirmButton = {
            Button(
                onClick = onConfirm,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Text("Delete", color = Color.White)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@Composable
fun GradeItemView(grade: Result, isSelected: Boolean, selectMode: Boolean, onItemClicked: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onItemClicked, enabled = selectMode),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = if (isSelected) Color.LightGray else Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = grade.courseid, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Grade: ${grade.grade} | Grade Point: ${grade.pointer}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGradeViewerScreen() {
    GradeViewerScreen(navController = rememberNavController())
}
