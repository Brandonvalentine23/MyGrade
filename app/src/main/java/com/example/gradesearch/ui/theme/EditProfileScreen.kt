package com.example.gradesearch.ui.theme

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gradesearch.data.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(navController: NavHostController, profileViewModel: ProfileViewModel = viewModel()) {
    val context = LocalContext.current
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
        Text(text = "Edit Profile", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Next)
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("E-mail") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next)
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = matricNumber,
            onValueChange = { matricNumber = it },
            label = { Text("Matric Number") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Next)
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = studyingCourse,
            onValueChange = { studyingCourse = it },
            label = { Text("Studying Course") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Next)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = {
            val user = User(
                username = name,
                useremail = email,
                matricNumber = matricNumber,
                studyingCourse = studyingCourse
            )
            profileViewModel.updateUser(user) {
                Toast.makeText(context, "Profile updated successfully", Toast.LENGTH_SHORT).show()
                navController.navigate("profile")
            }
        }) {
            Text(text = "Update Profile")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("profile") }) {
            Text(text = "Back to Profile")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEditProfileScreen() {
    EditProfileScreen(navController = rememberNavController())
}
