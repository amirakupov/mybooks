package com.example.myb.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.myb.viewmodel.BookViewModel
import com.example.myb.viewmodel.EditBookViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun EditBookScreen(
    navController: NavHostController,
    bookViewModel: BookViewModel,
    bookId: Int
) {
    val factory = EditBookViewModelFactory(bookViewModel, bookId)
    val editBookViewModel: EditBookViewModel = viewModel(factory = factory)

    val title by editBookViewModel.title.collectAsState()
    val author by editBookViewModel.author.collectAsState()
    val year by editBookViewModel.year.collectAsState()
    val isbn by editBookViewModel.isbn.collectAsState()
    val errorMessage by editBookViewModel.errorMessage.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Neues Buch anlegen/bearbeiten") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    value = title,
                    onValueChange = { editBookViewModel.onTitleChange(it) },
                    label = { Text("Titel") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = author,
                    onValueChange = { editBookViewModel.onAuthorChange(it) },
                    label = { Text("Autor*in") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = year,
                    onValueChange = { editBookViewModel.onYearChange(it) },
                    label = { Text("Jahr der Erstveröffentlichung") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = isbn,
                    onValueChange = { editBookViewModel.onIsbnChange(it) },
                    label = { Text("ISBN") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { editBookViewModel.validateAndSaveBook(navController) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Speichern")
                }
                if (errorMessage.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(errorMessage, color = MaterialTheme.colorScheme.error, fontSize = 16.sp)
                }
            }
        }
    )
}