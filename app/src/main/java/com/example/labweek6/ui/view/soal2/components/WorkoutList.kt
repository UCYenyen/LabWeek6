// Dalam nama Tuhan Yesus semoga dapat A :)
package com.example.labweek6.ui.view.soal2.components
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.labweek6.ui.model.soal2.Workout

@Composable
fun WorkoutList(
    workouts: List<Workout>,
    onClick: (Workout) -> Unit,
) {
    Column {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically), modifier = Modifier.padding(horizontal = 14.dp).fillMaxSize()) {
            if(!workouts.isEmpty()){
                workouts.forEach { workout ->
                    WorkoutItem(
                        workout = workout,
                        onClick = { onClick(workout) },
                        hasButton = true
                    )
                }
            }else{
                Text("No Workouts yet.", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Normal, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
            }
        }
    }
}