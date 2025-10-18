// Dalam nama Tuhan Yesus semoga dapat A :)
package com.example.labweek6.ui.view.soal2.components
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.labweek6.ui.model.soal2.User

@Composable
fun ProfileCard(user: User) {
    Column {
        Spacer(modifier = Modifier.height(8.dp))
        Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color(0xFFE6F0FA))) {
            Row(modifier = Modifier.padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Box(contentAlignment = Alignment.Center){
                    Column(modifier = Modifier.size(80.dp).background(color = Color(0xFFA5CDF6), shape = RoundedCornerShape(100.dp))) {

                    }
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "guest",
                        tint = Color.Black,
                        modifier = Modifier.size(56.dp)
                    )
                }
                Column(modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text("${user.name}, ${user.age}", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                    Text(
                        "${user.height}cm / ${user.weight}kg",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        ProfileStat(
                            label = "Workouts",
                            value = user.numberOfWorkouts.size.toString()
                        )
                        ProfileStat(label = "Friends", value = user.numberOfFriends.size.toString())
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileCardPreview() {
    ProfileCard(
        user = User(
            name = "Jamier Tanuwijaya",
            age = 21,
            height = 170,
            weight = 65,
            numberOfFriends = emptyList(),
            numberOfWorkouts =  emptyList(),
            isFriend = false
        )
    )
}