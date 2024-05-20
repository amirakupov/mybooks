package com.example.myb.ui

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myb.viewmodel.BookViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBooksApp() {
    val navController = rememberNavController()
    val bookViewModel: BookViewModel = viewModel()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("MyBooks App") })
        },
        content = {
            NavHost(navController = navController, startDestination = "book_list") {
                composable("book_list") {
                    BookListScreen(navController, bookViewModel)
                }
                composable("edit_book") {
                    EditBookScreen(navController, bookViewModel)
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyBooksApp()
}

