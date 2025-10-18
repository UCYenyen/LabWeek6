// Dalam nama Tuhan Yesus semoga dapat A :)
package com.example.labweek6.ui.view.soal2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.labweek6.ui.view.soal2.components.RecentlyAddedFriendList
import com.example.labweek6.ui.view.soal2.components.ProfileCard
import com.example.labweek6.ui.view.soal2.components.RecentWorkoutList
import com.example.labweek6.ui.viewmodel.soal2.Soal2ViewModel

@Composable
fun ProfileView(soal2ViewModel: Soal2ViewModel, navController: NavController = rememberNavController()) {
    val currentUser by soal2ViewModel.currentUser.collectAsState()
    val friends by soal2ViewModel.friends.collectAsState()
//    val workoutList by soal2ViewModel.currentUser.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 14.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        item {
            ProfileCard(user = currentUser)
        }
        item {
            RecentlyAddedFriendList(
                friends = friends,
                hasFriends = soal2ViewModel.checkAddedFriends(),
                onRemoveFriend = { friendObject ->
                    soal2ViewModel.removeFriend(friendObject)
                },
                onAddFriend = { friendObject ->
                    soal2ViewModel.addFriend(friendObject)
                }
            )
        }
        item {
            RecentWorkoutList(
                workouts = currentUser.numberOfWorkouts,
                onClick = { workoutObject ->
                    soal2ViewModel.toggleWorkoutStatus(workoutObject)
                },
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ProfileViewPreview() {
    ProfileView(soal2ViewModel = viewModel())
}