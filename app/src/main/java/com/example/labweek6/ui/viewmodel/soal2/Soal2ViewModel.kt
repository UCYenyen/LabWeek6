package com.example.labweek6.ui.viewmodel.soal2

import androidx.lifecycle.ViewModel
import com.example.labweek6.ui.dummy_datas.soal2.UserDummyDatas
import com.example.labweek6.ui.model.soal2.User
import com.example.labweek6.ui.model.soal2.Workout
import com.example.labweek6.ui.model.soal2.WorkoutFormState
import com.example.labweek6.ui.view.soal2.components.WorkoutIcon
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class Soal2ViewModel : ViewModel() {
    private val _currentUser = MutableStateFlow(
        User(
            name = "Jamier Tanuwijaya",
            age = 21,
            height = 170,
            weight = 65,
            numberOfFriends = emptyList(),
            numberOfWorkouts = emptyList()
        )
    )
    val currentUser: StateFlow<User> = _currentUser.asStateFlow()

    private val _suggestedFriends = MutableStateFlow<List<User>>(emptyList())
    val suggestedFriends: StateFlow<List<User>> = _suggestedFriends.asStateFlow()

    private val _workoutList = MutableStateFlow<List<Workout>>(emptyList())
    val workoutList: StateFlow<List<Workout>> = _workoutList.asStateFlow()

    private val _friends = MutableStateFlow<List<User>>(emptyList())
    val friends: StateFlow<List<User>> = _friends.asStateFlow()

    private val _isAddingWorkout = MutableStateFlow(false)
    val isAddingWorkout = _isAddingWorkout.asStateFlow()

    private val _state = MutableStateFlow(WorkoutFormState())
    val state: StateFlow<WorkoutFormState> = _state.asStateFlow()

    init {
        loadDummyData()
    }

    private fun validateForm(currentState: WorkoutFormState = _state.value) {
        val isValid = currentState.title.isNotBlank() &&
                currentState.type.isNotBlank() &&
                currentState.calories.isNotBlank() &&
                currentState.calories.all { it.isDigit() }
        _state.update { it.copy(isFormValid = isValid) }
    }

    fun onTitleChange(newTitle: String) {
        _state.update { it.copy(title = newTitle).also { newState -> validateForm(newState) } }
    }

    fun onTypeChange(newType: String) {
        _state.update { it.copy(type = newType).also { newState -> validateForm(newState) } }
    }

    fun onCaloriesChange(newCalories: String) {
        if (newCalories.all { it.isDigit() }) {
            _state.update { it.copy(calories = newCalories).also { newState -> validateForm(newState) } }
        }
    }

    fun onIconSelected(icon: WorkoutIcon) {
        _state.update { it.copy(selectedIcon = icon) }
    }

    fun saveWorkout() {
        if (!_state.value.isFormValid) return

        val currentState = _state.value
        val newWorkout = Workout(
            name = currentState.title,
            category = currentState.type,
            calories = currentState.calories.toIntOrNull() ?: 0,
            icon = currentState.selectedIcon.imageVector,
            added = true
        )

        _workoutList.update { currentList -> currentList + newWorkout }

        _currentUser.update { currentUser ->
            val updatedWorkouts = currentUser.numberOfWorkouts + newWorkout
            currentUser.copy(numberOfWorkouts = updatedWorkouts)
        }

        hideAddWorkoutForm()
    }

    private fun resetFormState() {
        _state.value = WorkoutFormState()
    }

    private fun loadDummyData() {
        _suggestedFriends.value = UserDummyDatas.dummyUsers.toList()
    }

    fun addFriend(friendToAdd: User) {
        _friends.update { currentFriends ->
            if (currentFriends.any { it.name == friendToAdd.name }) {
                currentFriends
            } else {
                currentFriends + friendToAdd
            }
        }

        _suggestedFriends.update { currentSuggestedFriends ->
            currentSuggestedFriends.map { friend ->
                if (friend.name == friendToAdd.name) {
                    friend.copy(isFriend = true)
                } else {
                    friend
                }
            }
        }

        _currentUser.update { currentUser ->
            currentUser.copy(numberOfFriends = friends.value)
        }
    }

    fun removeFriend(friendToRemove: User) {
        _friends.update { currentFriends ->
            currentFriends.filter { it.name != friendToRemove.name }
        }

        _suggestedFriends.update { currentSuggestedFriends ->
            currentSuggestedFriends.map { friend ->
                if (friend.name == friendToRemove.name) {
                    friend.copy(isFriend = false)
                } else {
                    friend
                }
            }
        }

        _currentUser.update { currentUser ->
            currentUser.copy(numberOfFriends = friends.value)
        }
    }
    fun checkAddedFriends() : Boolean {
        return friends.value.isNotEmpty()
    }

    fun toggleWorkoutStatus(workoutToToggle: Workout) {
        _workoutList.update { currentWorkouts ->
            currentWorkouts.map { workout ->
                if (workout.name == workoutToToggle.name) {
                    workout.copy(added = !workout.added)
                } else {
                    workout
                }
            }
        }

        _currentUser.update { currentUser ->
            val completed = _workoutList.value.filter { it.added }
            currentUser.copy(numberOfWorkouts = completed)
        }
    }
    fun showAddWorkoutForm() {
        _isAddingWorkout.value = true
    }

    fun hideAddWorkoutForm() {
        _isAddingWorkout.value = false
        resetFormState()
    }
}