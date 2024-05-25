package com.example.myb.ui

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myb.viewmodel.BookViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBooksApp() {
    val navController = rememberNavController()
    val bookViewModel: BookViewModel = viewModel()

    NavHost(navController = navController, startDestination = "book_list") {
        composable("book_list") {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text("Meine Lieblingsbücher") }
                    )
                },
                content = {
                    BookListScreen(navController, bookViewModel)
                }
            )
        }
        composable(
            route = "edit_book/{bookId}",
            arguments = listOf(navArgument("bookId") { type = NavType.IntType })
        ) { backStackEntry ->
            val bookId = backStackEntry.arguments?.getInt("bookId") ?: -1
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text("Neues Buch anlegen/bearbeiten") },
                        navigationIcon = {
                            IconButton(onClick = { navController.navigateUp() }) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = "Back"
                                )
                            }
                        }
                    )
                },
                content = {
                    EditBookScreen(navController, bookViewModel, bookId)
                }
            )
        }
    }
}
