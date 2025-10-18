// Dalam nama Tuhan Yesus semoga dapat A :)
package com.example.labweek6.ui.view.soal2.components
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.DirectionsRun
import androidx.compose.material.icons.outlined.RemoveCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.labweek6.ui.model.soal2.Workout

@Composable
fun WorkoutItem(
    workout: Workout,
    onClick: () -> Unit,
    hasButton: Boolean = true
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFEBF5EB))
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Column(
                        modifier = Modifier
                            .size(60.dp)
                            .background(
                                color = Color(0xFFB9E6BE),
                                shape = RoundedCornerShape(100.dp)
                            )
                    ) {}
                    Icon(contentDescription = "icon", imageVector = workout.icon, modifier = Modifier.size(30.dp))
                }
                Column {
                    Text(workout.name, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
                    Text(workout.calories.toString() + " Cals", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold)
                    Text(workout.category, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
                }
            }

            if(hasButton){
                Row {
                    if (workout.added) {
                        IconButton(onClick = onClick) {
                            Icon(Icons.Outlined.RemoveCircle, modifier = Modifier.size(32.dp), tint = Color(0xFFEB4C45), contentDescription = "Remove Workout")
                        }
                    } else {
                        IconButton(onClick = onClick) {
                            Icon(Icons.Filled.AddCircle, modifier = Modifier.size(32.dp), tint = Color(0xFF3B86F7), contentDescription = "Add Workout")
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun WorkoutItemPreview() {
    WorkoutItem(
        workout = Workout(name = "Morning Yoga", category = "Flexibility", calories = 10, icon = Icons.Default.DirectionsRun),
        onClick={}
    )
}
