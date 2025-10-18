// Dalam nama Tuhan Yesus semoga dapat A :)
package com.example.labweek6.ui.view.soal2.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.People
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileStat(label: String, value: String) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        Icon(imageVector = if(label == "Friends")Icons.Default.People else Icons.Default.LocalFireDepartment, contentDescription = "Icon", tint = if(label == "Friends") Color(0xFF3883F5) else Color(0xFFF09241))
        Text(value, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = if(label == "Friends") Color(0xFF3883F5) else Color(0xFFF09241))
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileStatPreview() {
    ProfileStat(label = "Followers", value = "150")
}