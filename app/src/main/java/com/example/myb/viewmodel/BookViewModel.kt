package com.example.myb.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myb.model.Book
import com.example.myb.model.BookDao
import com.example.myb.model.BookDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class BookViewModel(application: Application) : AndroidViewModel(application) {
    private val bookDao: BookDao = BookDatabase.getDatabase(application).bookDao()

    private val _books = bookDao.getAllBooks().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
    val books: StateFlow<List<Book>> = _books

    fun addBook(book: Book) {
        viewModelScope.launch {
            bookDao.insertBook(book)
        }
    }

    suspend fun getBookById(id: Int): Book? {
        return bookDao.getBookById(id)
    }

    fun updateBook(book: Book) {
        viewModelScope.launch {
            bookDao.updateBook(book)
        }
    }

    fun deleteBook(book: Book) {
        viewModelScope.launch {
            bookDao.deleteBook(book)
        }
    }

    fun toggleReadStatus(id: Int) {
        viewModelScope.launch {
            val book = bookDao.getBookById(id)
            if (book != null) {
                val updatedBook = book.copy(isRead = !book.isRead)
                bookDao.updateBook(updatedBook)
            }
        }
    }

    fun filterBooks(query: String): Flow<List<Book>> {
        return books.map { bookList ->
            bookList.filter { it.title.contains(query, ignoreCase = true) || it.author.contains(query, ignoreCase = true) }
        }
    }
}