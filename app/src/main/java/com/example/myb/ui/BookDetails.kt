package com.example.myb.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myb.model.Book
@Composable
fun BookDetails(book: Book) {
    Column(modifier = Modifier.padding(start = 16.dp)) {
        Text(text = "Year: ${book.year}", style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal))
        Text(text = "ISBN: ${book.isbn}", style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal))
    }
}

enum class SortOrder {
    ASCENDING,
    DESCENDING
}