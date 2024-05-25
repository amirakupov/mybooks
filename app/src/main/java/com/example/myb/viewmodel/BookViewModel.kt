package com.example.myb.viewmodel

import androidx.lifecycle.ViewModel
import com.example.myb.model.Book
import com.example.myb.model.dummyBooks
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class BookViewModel : ViewModel() {
    private val _books = MutableStateFlow(dummyBooks)
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

    fun deleteBook(id: Int) {
        val updatedList = _books.value.toMutableList()
        if (id in updatedList.indices) {
            updatedList.removeAt(id)
            _books.value = updatedList
        }
    }

    fun filterBooks(query: String): List<Book> {
        return _books.value.filter {
            it.title.contains(query, ignoreCase = true) ||
                    it.author.contains(query, ignoreCase = true)
        }
    }

    fun sortBooksAscending() {
        _books.value = _books.value.sortedBy { it.year }
    }

    fun sortBooksDescending() {
        _books.value = _books.value.sortedByDescending { it.year }
    }

    fun toggleReadStatus(id: Int) {
        val updatedList = _books.value.toMutableList()
        val book = updatedList[id]
        updatedList[id] = book.copy(isRead = !book.isRead)
        _books.value = updatedList
    }
}