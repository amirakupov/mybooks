package com.example.myb.viewmodel

import androidx.lifecycle.ViewModel
import com.example.myb.model.Book
import com.example.myb.model.dummyBooks
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class BookViewModel : ViewModel() {
//    private val _books = MutableStateFlow<List<Book>>(emptyList())
//    val books: StateFlow<List<Book>> get() = _books.asStateFlow()
    private val _books = MutableStateFlow(dummyBooks)
    val books: StateFlow<List<Book>> = _books

    var selectedBook: Book? = null
        private set

    init {
        _books.value = listOf(
            Book("Book 1", "Author 1", 2000, "1234567890123"),
            Book("Book 2", "Author 2", 2005, "2345678901234"),
            Book("Book 3", "Author 3", 2010, "3456789012345")
        )
    }

    fun saveBook(title: String, author: String, year: Int?, isbn: String) {
        if (title.isBlank() || author.isBlank() || year == null || year > 2023 || !isValidISBN(isbn)) {
            return
        }

        val book = Book(title, author, year, isbn)
        val currentBooks = _books.value.toMutableList()
        if (selectedBook == null) {
            currentBooks.add(book)
        } else {
            val index = currentBooks.indexOfFirst { it.isbn == selectedBook!!.isbn }
            if (index != -1) {
                currentBooks[index] = book
            }
        }
        _books.value = currentBooks
        selectedBook = null
    }

    fun deleteBook(book: Book) {
        _books.value = _books.value.filter { it.isbn != book.isbn }
    }

    fun setSelectedBook(book: Book) {
        selectedBook = book
    }

    private fun isValidISBN(isbn: String): Boolean {
        // Implement the ISBN validation logic here
        return true // Simplified for brevity
    }


    fun addBook(book: Book) {
        _books.value = _books.value + book
    }
}