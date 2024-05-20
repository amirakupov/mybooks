package com.example.myb.model

data class Book(val title: String, val author: String, val year: Int, val isbn: String)

val dummyBooks = listOf(
    Book("Book 1", "Author 1", 2000, "1234567890123"),
    Book("Book 2", "Author 2", 2005, "2345678901234"),
    Book("Book 3", "Author 3", 2010, "3456789012345")
)
