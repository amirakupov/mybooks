package com.example.myb.ui

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import com.example.myb.model.dummyBooks
import com.example.myb.viewmodel.BookViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookListScreen(navController: NavHostController, bookViewModel: BookViewModel) {
    val books by bookViewModel.books.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Meine Lieblingsbücher") }) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("edit_book") },
            ) {
                Text(text = "+")
            }
        },
        content = {
            BookList(books = books)
        }
    )
}