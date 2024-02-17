package com.example.dineshcompose.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class Features(
    val title: String,
    @DrawableRes val iconId : Int,
    val color: Color
)
