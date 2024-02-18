package com.example.dineshcompose.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.dineshcompose.navigation.MainNavigation
import com.example.dineshcompose.ui.activity.ui.theme.DineshComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DineshComposeTheme {
                MainNavigation(this)
            }
        }
    }
}