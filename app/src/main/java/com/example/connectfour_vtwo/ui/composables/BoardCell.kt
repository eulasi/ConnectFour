package com.example.connectfour_vtwo.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BoardCell(
    color: Color,
    onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(2.dp)
            .background(color, CircleShape)
            .clickable { onClick() }
            .size(50.dp)
    )
}