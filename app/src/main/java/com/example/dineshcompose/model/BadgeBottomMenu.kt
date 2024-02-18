package com.example.dineshcompose.model

import androidx.compose.ui.graphics.vector.ImageVector

data class BadgeBottomMenu(
    val title:String,
    val route: String,
    val icon: ImageVector,
    val badgeCount: Int = 0
)
