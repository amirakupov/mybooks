package com.example.myb.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myb.viewmodel.BookViewModel


//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun BookForm(navController: NavHostController, bookViewModel: BookViewModel) {
//    var title by remember { mutableStateOf(TextFieldValue(bookViewModel.selectedBook?.title ?: "")) }
//    var author by remember { mutableStateOf(TextFieldValue(bookViewModel.selectedBook?.author ?: "")) }
//    var year by remember { mutableStateOf(TextFieldValue(bookViewModel.selectedBook?.year?.toString() ?: "")) }
//    var isbn by remember { mutableStateOf(TextFieldValue(bookViewModel.selectedBook?.isbn ?: "")) }
//
//    Scaffold(
//        topBar = { TopAppBar(title = { Text(text = "Neues Buch anlegen/bearbeiten") }) }
//    ) { paddingValues ->
//        Column(modifier = Modifier.padding(paddingValues).padding(16.dp)) {
//            OutlinedTextField(
//                value = title,
//                onValueChange = { title = it },
//                label = { Text("Title") },
//                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
//            )
//            OutlinedTextField(
//                value = author,
//                onValueChange = { author = it },
//                label = { Text("Author") },
//                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
//            )
//            OutlinedTextField(
//                value = year,
//                onValueChange = { year = it },
//                label = { Text("Year") },
//                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
//            )
//            OutlinedTextField(
//                value = isbn,
//                onValueChange = { isbn = it },
//                label = { Text("ISBN") },
//                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
//            )
//
//            Button(
//                onClick = {
//                    bookViewModel.saveBook(title.text, author.text, year.text.toIntOrNull(), isbn.text)
//                    navController.popBackStack()
//                },
//                modifier = Modifier.align(Alignment.End).padding(vertical = 8.dp)
//            ) {
//                Text("Save")
//            }
//        }
//    }
//}