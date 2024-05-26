package com.example.myb.viewmodel


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.myb.model.Book
import kotlinx.coroutines.launch
import java.util.Calendar

class EditBookViewModel(private val bookViewModel: BookViewModel, private val bookId: Int) : ViewModel() {
    private val _title = MutableStateFlow("")
    val title: StateFlow<String> = _title

    private val _author = MutableStateFlow("")
    val author: StateFlow<String> = _author

    private val _year = MutableStateFlow("")
    val year: StateFlow<String> = _year

    private val _isbn = MutableStateFlow("")
    val isbn: StateFlow<String> = _isbn

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> = _errorMessage

    init {
        viewModelScope.launch {
            bookViewModel.getBookById(bookId)?.let { book ->
                _title.value = book.title
                _author.value = book.author
                _year.value = book.year.toString()
                _isbn.value = book.isbn
            }
        }
    }

    fun onTitleChange(newTitle: String) {
        _title.value = newTitle
    }

    fun onAuthorChange(newAuthor: String) {
        _author.value = newAuthor
    }

    fun onYearChange(newYear: String) {
        _year.value = newYear
    }

    fun onIsbnChange(newIsbn: String) {
        _isbn.value = newIsbn
    }

    fun validateAndSaveBook(navController: NavHostController) {
        viewModelScope.launch {
            _errorMessage.value = when {
                _title.value.isBlank() -> "Titel darf nicht leer sein"
                _author.value.isBlank() -> "Autor*in darf nicht leer sein"
                !isValidYear(_year.value) -> "Jahr der Erstveröffentlichung ist ungültig"
                !isValidISBN(_isbn.value) -> "ISBN ist ungültig"
                else -> {
                    val newBook = Book(
                        id = if (bookId == -1) 0 else bookId,
                        title = _title.value,
                        author = _author.value,
                        year = _year.value.toIntOrNull() ?: 0,
                        isbn = _isbn.value,
                        isRead = false
                    )
                    if (bookId == -1) {
                        bookViewModel.addBook(newBook)
                    } else {
                        bookViewModel.updateBook(newBook)
                    }
                    navController.navigateUp()
                    ""
                }
            }
        }
    }

    private fun isValidYear(year: String): Boolean {
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        return year.toIntOrNull()?.let { it in 1..currentYear } ?: false
    }

    private fun isValidISBN(isbn: String): Boolean {
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
}