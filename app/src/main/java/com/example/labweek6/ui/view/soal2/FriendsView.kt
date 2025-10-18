package com.example.labweek6.ui.view.soal2

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.labweek6.ui.view.soal2.components.SuggestedFriendList
import com.example.labweek6.ui.viewmodel.soal2.Soal2ViewModel

@Composable
fun FriendsView(soal2ViewModel: Soal2ViewModel){
    val suggestedFriends by soal2ViewModel.suggestedFriends.collectAsState()

    SuggestedFriendList (
        friends = suggestedFriends,
        onRemoveFriend = { friendObject ->
            soal2ViewModel.removeFriend(friendObject)
        },
        onAddFriend = { friendObject ->
            soal2ViewModel.addFriend(friendObject)
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun FriendsViewPreview() {
    FriendsView(soal2ViewModel = viewModel())
}
