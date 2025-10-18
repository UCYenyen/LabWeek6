package com.example.labweek6.ui.model.soal2

import androidx.compose.ui.graphics.vector.ImageVector

data class Workout(
    val name: String,
    val category: String,
    val calories: Int,
    val added: Boolean = false,
    val icon: ImageVector
)
