package com.example.labweek6.ui.view.soal2

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.labweek6.ui.view.soal2.components.AddWorkOutForm
import com.example.labweek6.ui.view.soal2.components.RecentWorkoutList
import com.example.labweek6.ui.view.soal2.components.WorkoutList
import com.example.labweek6.ui.viewmodel.soal2.Soal2ViewModel
import java.nio.file.WatchEvent

@Composable
fun WorkoutView(soal2ViewModel: Soal2ViewModel) {
    val workoutList by soal2ViewModel.workoutList.collectAsState()
    var isClicked by remember { mutableStateOf(false) }

    if (!workoutList.isEmpty()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                WorkoutList(
                    workouts = workoutList,
                    onClick = { workoutObject ->
                        soal2ViewModel.toggleWorkoutStatus(workoutObject)
                    },
                )
            }
        }
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "No Workouts Found.",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = 14.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End
    ) {
        Button(
            // --- INI PERBAIKANNYA ---
            onClick = { soal2ViewModel.showAddWorkoutForm() },
            shape = CircleShape,
            contentPadding = PaddingValues(0.dp),
            modifier = Modifier.size(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = Color.Blue
            )
        ) {
            Icon(
                Icons.Filled.AddCircle,
                contentDescription = "Add Workout",
                modifier = Modifier.size(56.dp)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun WorkoutViewPreview() {
    WorkoutView(viewModel())
}