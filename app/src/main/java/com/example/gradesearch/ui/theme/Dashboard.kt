package com.example.gradesearch.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gradesearch.R

@Composable
fun DashboardScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFB0C4DE))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "LOGOUT",
                color = Color.Red,
                modifier = Modifier
                    .clickable { navController.navigate("home") }
                    .padding(8.dp)
            )
        }
        Image(
            painter = painterResource(id = R.drawable.mygrade), // Replace with your image resource ID
            contentDescription = null,
            modifier = Modifier.size(180.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "DASHBOARD",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.Black,
            fontSize = 30.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                DashboardItem(
                    text = "COURSE MANAGEMENT",
                    imageResId = R.drawable.gradem, // Replace with your actual image resource ID
                    onClick = { navController.navigate("courseManagement") }
                )
                Spacer(modifier = Modifier.height(4.dp))
            }
            item {
                DashboardItem(
                    text = "GRADE ENTRY",
                    imageResId = R.drawable.gradeentry, // Replace with your actual image resource ID
                    onClick = { navController.navigate("GradeEntry") }
                )
                Spacer(modifier = Modifier.height(4.dp))
            }
            item {
                DashboardItem(
                    text = "GRADE VIEW",
                    imageResId = R.drawable.gradeview, // Replace with your actual image resource ID
                    onClick = { navController.navigate("gradeviewer") } // Navigate to Grade View
                )
                Spacer(modifier = Modifier.height(4.dp))
            }
            item {
                DashboardItem(
                    text = "PROFILE",
                    imageResId = R.drawable.profile, // Replace with your actual image resource ID
                    onClick = { navController.navigate("profile") } // Navigate to Profile
                )
            }
        }
    }
}

@Composable
fun DashboardItem(text: String, imageResId: Int, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(8.dp)
            .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
            .border(
                BorderStroke(2.dp, SolidColor(Color.Gray)),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = imageResId), // Dynamic image resource ID
            contentDescription = null,
            modifier = Modifier.size(55.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black,
            fontSize = 20.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDashboardScreen() {
    DashboardScreen(navController = rememberNavController())
}
