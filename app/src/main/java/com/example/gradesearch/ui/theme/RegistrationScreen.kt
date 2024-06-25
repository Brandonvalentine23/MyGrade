package com.example.gradesearch.ui.theme

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(
    navController: NavHostController,
    registerViewModel: RegisterViewModel = viewModel()
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var userType by remember { mutableStateOf("") }
    var userEmail by remember { mutableStateOf("") }
    var registrationError by remember { mutableStateOf(false) }
    var successMessage by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Register New Account", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Next)
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Next)
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = userType,
            onValueChange = { userType = it },
            label = { Text("User Type") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Next)
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = userEmail,
            onValueChange = { userEmail = it },
            label = { Text("E-mail") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Done)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = {
            coroutineScope.launch {
                registerViewModel.addUser(username, password, userType, userEmail, {
                    // Successfully registered
                    successMessage = true
                }, {
                    // Registration failed
                    registrationError = true
                })
            }
        }) {
            Text(text = "Register")
        }
        if (registrationError) {
            Text(text = "Registration failed", color = Color.Red)
        }
        if (successMessage) {
            Text(text = "Registration successful!", color = Color.Green)
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = {
                navController.navigate("login")
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
        ) {
            Text(text = "Back to Login")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRegistrationScreen() {
    val viewModel = RegisterViewModel(Application())
    RegistrationScreen(navController = rememberNavController(), registerViewModel = viewModel)
}
