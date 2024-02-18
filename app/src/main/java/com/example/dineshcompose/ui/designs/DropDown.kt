package com.example.dineshcompose.ui.designs

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DropDown(
    text: String,
    modifier: Modifier,
    initiallyOpened: Boolean = false
) {
    var isOpen by remember {
        mutableStateOf(initiallyOpened)
    }

    val alpha = animateFloatAsState(
        targetValue = if (isOpen) 1F else 0f,
        label = "Drop Down Animation",
        animationSpec = tween(
            durationMillis = 300
        )
    )

    val rotateX = animateFloatAsState(
        targetValue = if (isOpen) 0f else -90f,
        label = "Drop Down Animation",
        animationSpec = tween(
            durationMillis = 300
        )
    )

    Surface(
        color = Color.Black,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = text,
                    color = Color.White,
                    fontSize = 16.sp
                )
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Open Or Close Dropdown",
                    tint = Color.White,
                    modifier = Modifier
                        .clickable {
                            isOpen = !isOpen
                        }
                        .scale(1f, if (isOpen) -1f else 1f)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Box(modifier = Modifier
                .fillMaxWidth()
                .graphicsLayer {
                    transformOrigin = TransformOrigin(.5f, 0f)
                    rotationX = rotateX.value
                }
                .alpha(alpha.value)
            ) {
                Text(
                    text = "This is Now revealed!",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .background(Color.Green)
                )
            }

        }
    }
}