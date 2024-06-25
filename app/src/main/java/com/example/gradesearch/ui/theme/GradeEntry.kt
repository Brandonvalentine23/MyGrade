package com.example.gradesearch.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gradesearch.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GradeEntry(navController: NavHostController) {
    val viewModel: GradeViewModel2 = viewModel()
    val courseName by viewModel.courseName.collectAsState()
    val username by viewModel.username.collectAsState()
    val grade by viewModel.grade.collectAsState()
    val pointer by viewModel.pointer.collectAsState()

    val scrollState = rememberScrollState()
    var showMessage by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Grade Entry",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        },
        snackbarHost = {
            SnackbarHost(
                hostState = remember { SnackbarHostState() },
                modifier = Modifier.padding(16.dp)
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.tertiaryContainer),
            color = MaterialTheme.colorScheme.background
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                        .padding(16.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.mygrade),
                        contentDescription = stringResource(R.string.name),
                        modifier = Modifier
                            .size(150.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = stringResource(R.string.name),
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    TextField(
                        value = courseName,
                        onValueChange = { viewModel.courseName.value = it },
                        placeholder = { Text("Enter Course Name") },
                        modifier = Modifier.fillMaxWidth(0.8f)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    TextField(
                        value = username,
                        onValueChange = { viewModel.username.value = it },
                        placeholder = { Text("Enter Username") },
                        modifier = Modifier.fillMaxWidth(0.8f)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    TextField(
                        value = grade,
                        onValueChange = { viewModel.grade.value = it },
                        placeholder = { Text("Enter the Grade") },
                        modifier = Modifier.fillMaxWidth(0.8f)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    TextField(
                        value = pointer,
                        onValueChange = { viewModel.pointer.value = it },
                        placeholder = { Text("Enter the Pointer") },
                        modifier = Modifier.fillMaxWidth(0.8f),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(onClick = { viewModel.resetFields() }) {
                            Text("Cancel")
                        }
                        Button(onClick = {
                            viewModel.insertGrade()
                            showMessage = true
                        }) {
                            Text("Submit")
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            onClick = { navController.navigate("dashboard") },
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                        ) {
                            Text("Back")
                        }
                    }
                }
            }
        }
    }

    // Show snackbar when showMessage is true
    LaunchedEffect(showMessage) {
        if (showMessage) {
            navController.navigateUp()
            viewModel.resetFields()
            showMessage = false
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGradeEntryScreen() {
    val navController = rememberNavController()
    GradeEntry(navController = navController)
}
