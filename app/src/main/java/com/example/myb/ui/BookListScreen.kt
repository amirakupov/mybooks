package com.example.myb.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myb.model.Book
import com.example.myb.viewmodel.BookViewModel
import kotlinx.coroutines.flow.collectLatest


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookListScreen(navController: NavHostController, bookViewModel: BookViewModel) {
    var searchQuery by remember { mutableStateOf("") }
    var sortOrder by remember { mutableStateOf(SortOrder.ASCENDING) }
    val books by bookViewModel.books.collectAsState()
    val filteredBooks by produceState<List<Book>>(initialValue = emptyList(), searchQuery, sortOrder) {
        bookViewModel.filterBooks(searchQuery).collectLatest { bookList ->
            value = when (sortOrder) {
                SortOrder.ASCENDING -> bookList.sortedBy { it.year }
                SortOrder.DESCENDING -> bookList.sortedByDescending { it.year }
            }
        }
    }

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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                Spacer(modifier = Modifier.height(64.dp))
                TextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    label = { Text("Suche") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(onClick = {
                        sortOrder = if (sortOrder == SortOrder.ASCENDING) {
                            SortOrder.DESCENDING
                        } else {
                            SortOrder.ASCENDING
                        }
                    }) {
                        Text("Sortierung: ${if (sortOrder == SortOrder.ASCENDING) "Aufsteigend" else "Absteigend"}")
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                BookList(
                    books = filteredBooks,
                    onEditClick = { bookId -> navController.navigate("edit_book/$bookId") },
                    onDeleteClick = { book -> bookViewModel.deleteBook(book) },
                    onToggleReadStatus = { book -> bookViewModel.toggleReadStatus(book.id) }
                )
            }
        }
    )
}