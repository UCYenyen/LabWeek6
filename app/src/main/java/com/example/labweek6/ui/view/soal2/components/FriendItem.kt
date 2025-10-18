// Dalam nama Tuhan Yesus semoga dapat A :)
package com.example.labweek6.ui.view.soal2.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
fun FriendItem(
    friend: User,
    onAddFriend: (friend: User) -> Unit,
    onRemoveFriend: (friend: User) -> Unit,
    hasButton: Boolean = false
) {
    Card(
        modifier = Modifier
            .width(180.dp)
            .height(240.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE6F5FB))
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 14.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(14.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(contentAlignment = Alignment.Center) {
                Column(
                    modifier = Modifier
                        .size(80.dp)
                        .background(color = Color(0xFFABE2F0), shape = RoundedCornerShape(100.dp))
                ) {

                }
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "guest",
                    tint = Color.Black,
                    modifier = Modifier.size(56.dp)
                )
            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(friend.name, fontWeight = FontWeight.Bold, fontSize = 24.sp)
                Text(
                    "${friend.age} years old",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            }
            if (hasButton) {
                Button(
                    onClick = { if(friend.isFriend) onRemoveFriend(friend) else onAddFriend(friend) },
                    colors = ButtonColors(
                        containerColor = if(friend.isFriend) Color(0xFFE15656) else Color(0xFF3B86F7),
                        contentColor = Color.White,
                        disabledContainerColor = Color.Gray,
                        disabledContentColor = Color.White
                    ),
                    contentPadding = PaddingValues(horizontal = 24.dp, vertical = 0.dp)
                ) {
                    Text(if (friend.isFriend) "Unfriend" else "Add Friend")
                }
            }
        }
    }
}