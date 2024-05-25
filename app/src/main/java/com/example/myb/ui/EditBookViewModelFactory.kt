package com.example.myb.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


import com.example.myb.viewmodel.BookViewModel
import com.example.myb.viewmodel.EditBookViewModel

class EditBookViewModelFactory(
    private val bookViewModel: BookViewModel,
    private val bookId: Int
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditBookViewModel::class.java)) {
            return EditBookViewModel(bookViewModel, bookId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
