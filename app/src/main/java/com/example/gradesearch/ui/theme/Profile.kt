package com.example.gradesearch.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gradesearch.data.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Profile(navController: NavHostController, profileViewModel: ProfileViewModel = viewModel()) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var matricNumber by remember { mutableStateOf("") }
    var studyingCourse by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        profileViewModel.getUser("A1234567") { user -> // Replace with actual matric number
            user?.let {
                name = it.username
                email = it.useremail
                matricNumber = it.matricNumber
                studyingCourse = it.studyingCourse
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFB0C4DE))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Profile", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Name: $name")
        Spacer(modifier = Modifier.height(8.dp))
        Text("E-mail: $email")
        Spacer(modifier = Modifier.height(8.dp))
        Text("Matric Number: $matricNumber")
        Spacer(modifier = Modifier.height(8.dp))
        Text("Studying Course: $studyingCourse")
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = { navController.navigate("editProfile") }) {
            Text(text = "Edit Profile")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("dashboard") }) {
            Text(text = "Back to Dashboard")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProfile() {
    Profile(navController = rememberNavController())
}
