package com.example.gradesearch.ui.theme

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gradesearch.R
import com.example.gradesearch.data.Course
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseManagement(navController: NavHostController, courseViewModel: CourseViewModel = viewModel()) {
    var courseid by remember { mutableStateOf(TextFieldValue("")) }
    var coursename by remember { mutableStateOf(TextFieldValue("")) }
    var lecturer by remember { mutableStateOf(TextFieldValue("")) }
    var departmentname by remember { mutableStateOf(TextFieldValue("")) }
    var academicyear by remember { mutableStateOf(TextFieldValue("")) }
    var userid by remember { mutableStateOf(TextFieldValue("")) }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header with Title
        Text(
            text = "Course Management",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 16.dp),
            style = MaterialTheme.typography.headlineMedium
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxSize().padding(16.dp)
        ) {
            TextField(
                value = courseid,
                onValueChange = { courseid = it },
                label = { Text("Course ID") },
                modifier = Modifier.fillMaxWidth(0.8f)
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = coursename,
                onValueChange = { coursename = it },
                label = { Text("Course Name") },
                modifier = Modifier.fillMaxWidth(0.8f)
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = lecturer,
                onValueChange = { lecturer = it },
                label = { Text("Lecturer") },
                modifier = Modifier.fillMaxWidth(0.8f)
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = departmentname,
                onValueChange = { departmentname = it },
                label = { Text("Department Name") },
                modifier = Modifier.fillMaxWidth(0.8f)
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = academicyear,
                onValueChange = { academicyear = it },
                label = { Text("Academic Year") },
                modifier = Modifier.fillMaxWidth(0.8f)
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = userid,
                onValueChange = { userid = it },
                label = { Text("User ID") },
                modifier = Modifier.fillMaxWidth(0.8f)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = {
                if (courseid.text.isNotEmpty() && coursename.text.isNotEmpty() && lecturer.text.isNotEmpty() && departmentname.text.isNotEmpty() && academicyear.text.isNotEmpty() && userid.text.isNotEmpty()) {
                    try {
                        courseViewModel.addCourse(
                            courseid = courseid.text,
                            coursename = coursename.text,
                            lecturer = lecturer.text,
                            departmentname = departmentname.text,
                            academicyear = academicyear.text,
                            userid = userid.text
                        ) {
                            Toast.makeText(context, "Course added successfully", Toast.LENGTH_SHORT).show()
                            courseid = TextFieldValue("")
                            coursename = TextFieldValue("")
                            lecturer = TextFieldValue("")
                            departmentname = TextFieldValue("")
                            academicyear = TextFieldValue("")
                            userid = TextFieldValue("")
                        }
                        Log.d("CVM_ADDCOURSE", "Successful")
                    } catch (e: Exception) {
                        Log.e("CVM_ADDCOURSE_ERR", "ERROR ENCOUNTERED: $e")
                    }
                } else {
                    Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                }
            }) {
                Text("Add Course")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.navigate("dashboard") }) {
                Text("Back")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCourseManagement() {
    CourseManagement(navController = rememberNavController())
}
