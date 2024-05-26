package com.example.myb.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class Book(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val author: String,
    val year: Int,
    val isbn: String,
    var isRead: Boolean = false
)

//val dummyBooks = listOf(
//    Book("Book 1", "Author 1", 2000, "1234567890123"),
//
//)
