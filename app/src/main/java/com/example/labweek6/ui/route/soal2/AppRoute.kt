package com.example.labweek6.ui.route.soal2

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.PersonPin
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.labweek6.ui.view.soal2.FriendsView
import com.example.labweek6.ui.view.soal2.ProfileView
import com.example.labweek6.ui.view.soal2.WorkoutView
import com.example.labweek6.ui.view.soal2.components.AddWorkOutForm
import com.example.labweek6.ui.viewmodel.soal2.Soal2ViewModel

enum class AppView(val title: String, val icon: ImageVector? = null) {
    Profile("Profile", Icons.Filled.PersonPin),
    Workouts("Workouts", Icons.Filled.LocalFireDepartment),
    Friends("Friends", Icons.Filled.People),
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppRoute() {
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val currentRoute = currentDestination?.route
    val currentView = AppView.entries.find { it.name == currentRoute }

    val bottomNavItems = listOf(
        BottomNavItem(AppView.Profile, label = "ProfileView"),
        BottomNavItem(AppView.Workouts, label = "Workouts"),
        BottomNavItem(AppView.Friends, label = "Friends"),
    )

    val viewModel : Soal2ViewModel = viewModel()

    val isAddingWorkout by viewModel.isAddingWorkout.collectAsState()
    val formState by viewModel.state.collectAsState()

    Scaffold(topBar = {
        MyTopAppBar(
            currentView = currentView,
        )},
        bottomBar = {
            MyBottomNavigationBar(
                navController = navController,
                currentDestination = currentDestination,
                items = bottomNavItems
            )
        }
    ) { innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination = AppView.Profile.name
        ) {
            composable(route = AppView.Profile.name) {
                ProfileView(viewModel, navController = navController)
            }
            composable(route = AppView.Workouts.name) {
                WorkoutView(viewModel)
            }
            composable(route = AppView.Friends.name) {
                FriendsView(viewModel)
            }
        }
        AddWorkOutForm(
            isActive = isAddingWorkout,
            state = formState,
            onDismiss = viewModel::hideAddWorkoutForm,
            onSave = viewModel::saveWorkout,
            onTitleChange = viewModel::onTitleChange,
            onTypeChange = viewModel::onTypeChange,
            onCaloriesChange = viewModel::onCaloriesChange,
            onIconSelected = viewModel::onIconSelected
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    currentView: AppView?,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(text = currentView?.title ?: AppView.Profile.title, style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.ExtraBold)
        },
        modifier = modifier
    )
}

@Composable
fun MyBottomNavigationBar(
    navController: NavHostController,
    currentDestination: NavDestination?,
    items: List<BottomNavItem>
) {
    if (items.any { it.view.name == currentDestination?.route }) {
        NavigationBar {
            items.forEach { item ->
                NavigationBarItem(
                    icon = { Icon(item.view.icon!!, contentDescription = item.label) },
                    label = { Text(item.label) },
                    selected = currentDestination?.hierarchy?.any { it.route == item.view.name } == true,
                    onClick = {
                        navController.navigate(item.view.name) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}

data class BottomNavItem(val view: AppView, val label: String)