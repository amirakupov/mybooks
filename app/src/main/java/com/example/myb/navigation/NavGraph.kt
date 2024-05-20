package com.example.myb.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myb.ui.BookList
import com.example.myb.ui.theme.BookForm
import com.example.myb.viewmodel.BookViewModel

//sealed class Screen(val route: String) {
//    object BookList : Screen("book_list")
//    object BookForm : Screen("book_form")
//}
//
//@Composable
//fun NavGraph(navController: NavHostController, bookViewModel: BookViewModel) {
//    NavHost(navController = navController, startDestination = Screen.BookList.route) {
//        composable(Screen.BookList.route) {
//            BookList(navController, bookViewModel)
//        }
//        composable(Screen.BookForm.route) {
//            BookForm(navController, bookViewModel)
//        }
//    }
//}