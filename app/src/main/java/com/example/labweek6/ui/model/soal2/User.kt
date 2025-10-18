package com.example.labweek6.ui.model.soal2

data class User(
    val name: String,
    val age: Int,
    val height: Int,
    val weight: Int,
    val numberOfFriends: List<User>,
    val numberOfWorkouts: List<Workout>,
    val isFriend: Boolean = false
)
