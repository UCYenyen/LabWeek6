package com.example.labweek6.ui.model.soal2

import com.example.labweek6.ui.dummy_datas.soal2.AvailableIcons
import com.example.labweek6.ui.view.soal2.components.WorkoutIcon

data class WorkoutFormState(
    val title: String = "",
    val type: String = "",
    val calories: String = "",
    val selectedIcon: WorkoutIcon = AvailableIcons.availableIcons.first(),
    val iconList: List<WorkoutIcon> = AvailableIcons.availableIcons,
    val isFormValid: Boolean = false
)