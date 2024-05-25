package com.example.myb.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.myb.model.Book

import com.example.myb.viewmodel.BookViewModel


@Composable
fun BookList(
    books: List<Book>,
    onEditClick: (Int) -> Unit,
    onDeleteClick: (Int) -> Unit,
    onToggleReadStatus: (Int) -> Unit
) {
    LazyColumn {
        itemsIndexed(books) { index, book ->
            BookListItem(book = book, bookId = index, onEditClick = onEditClick, onDeleteClick = onDeleteClick, onToggleReadStatus = onToggleReadStatus)
            Divider(color = Color.Gray, thickness = 1.dp)
        }
    }
}
