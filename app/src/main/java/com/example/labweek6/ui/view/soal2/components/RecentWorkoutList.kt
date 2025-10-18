// Dalam nama Tuhan Yesus semoga dapat A :)
package com.example.labweek6.ui.view.soal2.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.labweek6.ui.model.soal2.Workout

@Composable
fun RecentWorkoutList(
    workouts: List<Workout>,
    onClick: (Workout) -> Unit,
) {
    if(!workouts.isEmpty()){
        Column {
            Text("Workout List", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                workouts.takeLast(3).forEach { workout ->
                    WorkoutItem(
                        workout = workout,
                        onClick = { onClick(workout) },
                        hasButton = false
                    )
                }
            }
        }
    }else{
        Column(modifier = Modifier.fillMaxWidth().height(180.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Text("No workouts yet.", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Normal, textAlign = TextAlign.Center, fontSize = 18.sp, color = Color.Gray)
        }
    }
}