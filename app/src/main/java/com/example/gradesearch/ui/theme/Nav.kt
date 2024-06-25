package com.example.gradesearch.ui.theme

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomePage(navController) }
        composable("login") { LoginScreen(navController) }
        composable("register") { RegistrationScreen(navController) }
        composable("dashboard") { DashboardScreen(navController) }
        composable("gradeviewer") { GradeViewerScreen(navController) }
        composable("profile") { Profile(navController) }
        composable("editProfile") { EditProfileScreen(navController) }
        composable("CourseManagement") { CourseManagement(navController) }
        composable("GradeEntry") { GradeEntry(navController) }
    }
}
