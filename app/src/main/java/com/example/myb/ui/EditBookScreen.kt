package com.example.myb.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.myb.model.Book
import com.example.myb.viewmodel.BookViewModel
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun EditBookScreen(navController: NavHostController, bookViewModel: BookViewModel, bookId: Int) {
    val book = bookViewModel.getBookById(bookId)
    var title by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }
    var isbn by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    fun isValidYear(year: String): Boolean {
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        return year.toIntOrNull()?.let { it in 1..currentYear } ?: false
    }

    fun isValidISBN(isbn: String): Boolean {
        // Check for length and basic format
        val regex = Regex("\\d{3}-\\d-\\d{2}-\\d{6}-\\d")
        if (!isbn.matches(regex)) return false

        // Remove hyphens for digit-only operations
        val digits = isbn.replace("-", "")

        // Ensure we have exactly 13 digits
        if (digits.length != 13) return false

        // Compute the check digit using ISBN-13 algorithm
        val sum = digits.substring(0, 12).mapIndexed { index, c ->
            val digit = c.toString().toInt()
            if (index % 2 == 0) digit else digit * 3
        }.sum()

        val checkDigit = (10 - (sum % 10)) % 10

        // The check digit is the last digit of the ISBN
        return checkDigit == digits.last().toString().toInt()
    }
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Titel") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = author,
            onValueChange = { author = it },
            label = { Text("Autor*in") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = year,
            onValueChange = { year = it },
            label = { Text("Jahr der Erstveröffentlichung") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = isbn,
            onValueChange = { isbn = it },
            label = { Text("ISBN") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                errorMessage = when {
                    title.isBlank() -> "Titel darf nicht leer sein"
                    author.isBlank() -> "Autor*in darf nicht leer sein"
                    !isValidYear(year) -> "Jahr der Erstveröffentlichung ist ungültig"
                    !isValidISBN(isbn) -> "ISBN ist ungültig"
                    else -> {
                        val newBook = Book(title, author, year.toIntOrNull() ?: 0, isbn)
                        if (bookId == -1) {
                            bookViewModel.addBook(newBook)
                        } else {
                            bookViewModel.updateBook(bookId, newBook)
                        }
                        navController.navigateUp()
                        ""
                    }
                }
            },
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
