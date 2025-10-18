// Dalam nama Tuhan Yesus semoga dapat A :)
package com.example.labweek6.ui.view.soal2.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import com.example.labweek6.ui.model.soal2.User

@Composable
fun RecentlyAddedFriendList(
    friends: List<User>,
    onAddFriend: (friend: User) -> Unit,
    hasFriends: Boolean,
    onRemoveFriend: (friend: User) -> Unit
) {
    Column {
        Text(
            "Recently Added Friends",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
//        LazyRow {
//            item {  }
//        }
        if (hasFriends) {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(horizontal = 4.dp)
            ) {
                items(friends.takeLast(3), key = { it.name }) { friend ->
                    FriendItem(friend = friend, onAddFriend = onAddFriend, hasButton = false, onRemoveFriend = onRemoveFriend)
                }
            }
        }else{
            Column(modifier = Modifier.fillMaxWidth().height(180.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Text("No Friends yet.", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Normal, textAlign = TextAlign.Center, fontSize = 18.sp, color = Color.Gray)
            }
        }

    }
}