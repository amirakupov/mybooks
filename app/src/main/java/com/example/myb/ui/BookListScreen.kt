package com.example.myb.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myb.model.Book
import com.example.myb.viewmodel.BookViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookListScreen(navController: NavHostController, bookViewModel: BookViewModel) {
    val books by bookViewModel.books.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Meine Lieblingsbücher") })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("edit_book/-1") },
            ) {
                Text(text = "+")
            }
        },
        content = {
            BookList(books = books, onEditClick = { bookId ->
                navController.navigate("edit_book/$bookId")
            })
        }
    )
}
