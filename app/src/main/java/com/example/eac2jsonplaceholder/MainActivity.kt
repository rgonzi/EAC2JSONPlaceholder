package com.example.eac2jsonplaceholder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.eac2jsonplaceholder.ui.EAC2JsonPlaceHolderApp
import com.example.eac2jsonplaceholder.ui.theme.EAC2JSONPlaceholderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EAC2JSONPlaceholderTheme {
                EAC2JsonPlaceHolderApp()
            }
        }
    }
}