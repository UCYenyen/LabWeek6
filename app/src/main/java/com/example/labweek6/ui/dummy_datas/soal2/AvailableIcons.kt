package com.example.labweek6.ui.dummy_datas.soal2

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.DirectionsRun
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.LocalFireDepartment
import com.example.labweek6.ui.view.soal2.components.WorkoutIcon


object AvailableIcons {
    val availableIcons =  listOf(
        WorkoutIcon("fire", Icons.Default.LocalFireDepartment),
        WorkoutIcon("heart", Icons.Default.Favorite),
        WorkoutIcon("bolt", Icons.Default.Bolt),
        WorkoutIcon("person", Icons.Default.DirectionsRun),
        WorkoutIcon("run", Icons.Default.FitnessCenter)
    )
}