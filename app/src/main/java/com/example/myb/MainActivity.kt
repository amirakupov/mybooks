package com.example.myb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.myb.ui.MyBooksApp
import com.example.myb.ui.theme.MyBTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyBTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    MyBooksApp()
                }
            }
        }
    }
}
