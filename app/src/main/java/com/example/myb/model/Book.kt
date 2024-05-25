package com.example.myb.model

data class Book(
    val title: String,
    val author: String,
    val year: Int,
    val isbn: String,
    var isRead: Boolean = false
)

val dummyBooks = listOf(
    Book("Book 1", "Author 1", 2000, "1234567890123"),

)
