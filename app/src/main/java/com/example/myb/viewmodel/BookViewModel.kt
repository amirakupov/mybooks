package com.example.myb.viewmodel

import androidx.lifecycle.ViewModel
import com.example.myb.model.Book
import com.example.myb.model.dummyBooks
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class BookViewModel : ViewModel() {
    // Private backing property
    private val _books = MutableStateFlow(dummyBooks)
    // Public immutable property
    val books: StateFlow<List<Book>> = _books

    fun addBook(book: Book) {
        _books.value = _books.value + book
    }
    fun getBookById(id: Int): Book? {
        return _books.value.getOrNull(id)
    }

    fun updateBook(id: Int, updatedBook: Book) {
        val updatedList = _books.value.toMutableList()
        updatedList[id] = updatedBook
        _books.value = updatedList
    }
}