package com.example.labweek6.ui.view.soal2.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.labweek6.ui.model.soal2.User
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items

@Composable
fun SuggestedFriendList(
    friends: List<User>,
    onAddFriend: (friend: User) -> Unit,
    onRemoveFriend: (friend: User) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(8.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 14.dp),
            modifier = Modifier.fillMaxSize() // adjust height as needed
        ) {
            items(friends, key = { it.name }) { friend ->
                FriendItem(
                    friend = friend,
                    onAddFriend = onAddFriend,
                    hasButton = true,
                    onRemoveFriend = onRemoveFriend
                )
            }
        }
    }
}